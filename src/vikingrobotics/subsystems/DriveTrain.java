/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package vikingrobotics.subsystems;

import vikingrobotics.misc.Constants;
import vikingrobotics.misc.Debug;
import vikingrobotics.misc.RobotMap;
import vikingrobotics.commands.CommandBase;
import vikingrobotics.commands.drivetrain.DriveWithJoystick;
import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem for the drivetrain. Using tank drive.
 * @author Neal
 */
public class DriveTrain extends Subsystem implements Constants {
	
	private RobotDrive drive;
	private Jaguar leftJag, rightJag;
//	private Encoder leftEncoder, rightEncoder;
	private AnalogChannel sonar;
	private static double direction = 1;
	private boolean isBalancedOnBridge = false;

	public DriveTrain() {		
		super("DriveTrain");
		leftJag = new Jaguar(RobotMap.kDriveLeftMotor);
		rightJag = new Jaguar(RobotMap.kDriveRightMotor);
		drive = new RobotDrive(leftJag, rightJag);
		drive.setSafetyEnabled(false);

		Debug.println("[robot] Initializing left drive motor on channel " + RobotMap.kDriveLeftMotor);
		Debug.println("[robot] Initializing right drive motor on channel " + RobotMap.kDriveRightMotor);
		
		drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
		drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
				
//		leftEncoder = new Encoder(RobotMap.kEncoderLeftA, RobotMap.kEncoderLeftB, false);
//		rightEncoder = new Encoder(RobotMap.kEncoderRightA, RobotMap.kEncoderRightB, false);
		
//		leftEncoder.start();
//		rightEncoder.start();
		
		sonar = new AnalogChannel(RobotMap.kSonarChannel);
		
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick());
	}
	
	public double getSonarDistance() {
		// MaxSonar EZ1 input units are in (Vcc/512) / inch; multiply by (512/Vcc) to get inches.
		return sonar.getVoltage() * 512 / 5;	// Double check .1v == 1 feet || .01v == 1 inch
	}
	
	public AnalogChannel getSonar() {
		return sonar;
	}
	
	public void setForwards() {
		direction = 1;
	}
	
	public void setBackwards() {
		direction = -1;
	}
	
	public void stop() {
		drive.tankDrive(0.0, 0.0);
	}
	
	public boolean canDrive() {
		return !(CommandBase.oi.getDS().getDigitalIn(kDSDigitalInputDisableDrive));
	}
	
	public void tankDrive(double leftValue, double rightValue) {
		leftValue *= direction;
		rightValue *= direction;
		if(canDrive())
			drive.tankDrive(leftValue, rightValue);
	}
	
	public void arcadeDrive(double moveValue, double rotateValue) {
		moveValue *= direction;
		rotateValue *= direction;
		if(canDrive())
			drive.arcadeDrive(moveValue, rotateValue);
	}
	
	public void straight(double speed) {
		speed *= direction;
		if(canDrive())
			drive.tankDrive(speed, speed);
	}
	
}
