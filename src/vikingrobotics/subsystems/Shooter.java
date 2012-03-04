/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package vikingrobotics.subsystems;

import vikingrobotics.commands.shooter.ShooterMove;
import vikingrobotics.misc.Debug;
import vikingrobotics.misc.RobotMap;
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
	private double speed = 0.0;
	
	public Shooter() {
		super("Shooter");
		Debug.println("[Shooter] Initializing FlyWheel jaguar on channel " + RobotMap.kShooterFlyWheelChannel);
		flyWheel = new Jaguar(RobotMap.kShooterFlyWheelChannel);
		Debug.println("[Shooter] Initializing Feeder jaguar on channel " + RobotMap.kShooterFeederChannel);
		feeder = new Jaguar(RobotMap.kShooterFeederChannel);
		Debug.println("[Shooter] Initializing Angler relay on channel " + RobotMap.kShooterAnglerChannel);
		angler = new Relay(RobotMap.kShooterAnglerChannel);
		Debug.println("[Shooter] Initializing gyro on channel " + RobotMap.kGyroChannel);
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new ShooterMove());		
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public double getSpeed() {
		return this.speed;
	}
	
	public void run() {
		flyWheel.set(this.speed);
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

}
