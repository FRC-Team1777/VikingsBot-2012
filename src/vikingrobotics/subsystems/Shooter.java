/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package vikingrobotics.subsystems;

import vikingrobotics.commands.CommandBase;
import vikingrobotics.misc.Debug;
import vikingrobotics.misc.RobotMap;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Subsystem for the shooter. Includes the fly wheel motor, the feeder and the angle setter.
 * @author Neal
 */
public class Shooter extends Subsystem {
	
	private Jaguar flyWheel;
	private Jaguar feeder;
	private Relay angler;
	private Gyro gyro;
	private double currentAngle, previousAngle;
	private double speed = 1.0;
	private boolean angleSet = false;
	private final static double speedTolerance = 2.0;
	private final static double angleTolerance = 1.0;
	private final static double defaultGyroAngle = 30;
	
	public Shooter() {
		super("Shooter");
		flyWheel = new Jaguar(RobotMap.kShooterFlyWheelChannel);
		feeder = new Jaguar(RobotMap.kShooterFeederChannel);
		angler = new Relay(RobotMap.kShooterAnglerChannel);
		Debug.println("[robot] Initializing shooter motor on channel " + RobotMap.kShooterFlyWheelChannel);
		Debug.println("[robot] Initializing feeder motor on channel " + RobotMap.kShooterFeederChannel);
		Debug.println("[robot] Initializing angler relay on channel " + RobotMap.kShooterAnglerChannel);
		gyro = new Gyro(RobotMap.kGyroChannel);
		gyro.setSensitivity(0.007);
		gyro.reset();
		currentAngle = defaultGyroAngle + SmartDashboard.getDouble("TestDouble", 0.0);
		previousAngle = currentAngle;
//		resetCurrentGyroAngle();
	}
	
	public void initDefaultCommand() {}
	
	// Set manual speed for the flyWheel
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public double getSpeed() {
		return this.speed;
	}
	public double getActualSpeed() {
		return flyWheel.get();
	}
	
	public void run() {
//		flyWheel.set(this.speed);
//limit is the amount of change you will allow every iteration
//limitedJoystick is the rate-limited joystick value you use to control your motors.
		double limit = 0.075;
		double joystick = this.speed;
		double limitedJoystick = flyWheel.getSpeed();
		double change = joystick - limitedJoystick;
		if (change>limit) change = limit;
		else if (change<-limit) change = -limit;
		limitedJoystick += change;
		flyWheel.set(limitedJoystick);
	}
	public void run(double speed) {
		setSpeed(speed);
		this.run();
	}
	
	public void stop() {
		this.run(0.0);
	}
	
	public void setFeederForward() {
		feeder.set(-1.0);
	}
	public void setFeederReverse() {
		feeder.set(1.0);
	}
	
	public void stopFeeder() {
		feeder.set(0.0);
	}
	
	public void moveUp() {
		SmartDashboard.putString("ShooterMove", "Up");
		angler.set(Relay.Value.kForward);
	}
	public void moveDown() {
		SmartDashboard.putString("ShooterMove", "Down");
		angler.set(Relay.Value.kReverse);
	}
	public void moveStop() {
		SmartDashboard.putString("ShooterMove", "Stop");
		angler.set(Relay.Value.kOff);
	}
	
	// Set the current shooter angle
	public void setAngle(double angle) {
		if (this.currentAngle != angle) {
			this.previousAngle = currentAngle;
			this.currentAngle = angle;
		}
	}
	
	public void resetGyro() {
		gyro.reset();
	}
	
	public void resetCurrentGyroAngle() {
		this.currentAngle = defaultGyroAngle + ((CommandBase.oi.getDS().getAnalogIn(2) * 10) - 25);
	}
	
	public void changingAngle() {
		this.currentAngle -= gyro.getAngle();
	}
	
	public double getGyroAngle() {
		return currentAngle;
	}
	

	/**
	 * Checks if the current RPM is within the
	 * tolerance range of the desired RPM.
	 * @return atSetPoint
	 */
	public boolean isAtSetPoint() {
		Debug.println("FlywheelSpeed: " +flyWheel.get()+ "\tSpeed: " +this.speed+ "\tDiff: " +(Math.abs(flyWheel.get() - this.speed)));
		return (Math.abs(flyWheel.get() - this.speed) < 0.05);  // FIND TOLERANCE_RPM
	}

}
