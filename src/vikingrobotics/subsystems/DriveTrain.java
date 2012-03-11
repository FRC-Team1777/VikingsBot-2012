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
	private AnalogChannel sonar;

	public DriveTrain() {
		super("DriveTrain");
		Debug.println("[DriveTrain] Initializing left jaguar on channel " + RobotMap.kDriveLeftMotor);
		leftJag = new Jaguar(RobotMap.kDriveLeftMotor);
		Debug.println("[DriveTrain] Initializing right jaguar on channel " + RobotMap.kDriveRightMotor);
		rightJag = new Jaguar(RobotMap.kDriveRightMotor);

		drive = new RobotDrive(leftJag, rightJag);
		drive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
		drive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
		drive.setSafetyEnabled(true);
		
		sonar = new AnalogChannel(RobotMap.kSonarChannel);
		Debug.println("[DriveTrain] Initializing sonar on channel " + RobotMap.kSonarChannel);
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
	
	/**
	 * Stop driving
	 */
	public void stop() {
		drive.stopMotor();
	}
	
	/**
	 * Tell if the robot is allowed to drive or not. It is decided by the Digital Input for DisableDrive.
	 * If Digital Input for DisableDrive is on, we return false and all the drive motors will not run
	 * at all, not matter what command asks it to.
	 * @return Whether or not the drive motors are allowed to run
	 */
	public boolean canDrive() {
		return !(CommandBase.oi.getDS().getDS().getDigitalIn(kDSDigitalInputDisableDrive));
	}
	
	/**
	 * Provide tank steering using the stored robot configuration.
	 * This function lets you directly provide joystick values from any source.
	 * @param leftValue The value of the left stick.
	 * @param rightValue The value of the right stick.
	 */
	public void tankDrive(double leftValue, double rightValue) {
		if(canDrive())
			drive.tankDrive(leftValue, rightValue);
	}
	
	/**
	 * Arcade drive implements single stick driving.
	 * This function lets you directly provide joystick values from any source.
	 * @param moveValue The value to use for fowards/backwards
	 * @param rotateValue The value to use for the rotate right/left
	 */
	public void arcadeDrive(double moveValue, double rotateValue) {
		if(canDrive())
			drive.arcadeDrive(moveValue, rotateValue);
	}
	
	/**
	 * Drive in a straight direction. Since the left side has smaller sprocket, it has more
	 * torque, thus less speed. So, in order for it to drive straight, right motor needs to
	 * drive at 75% speed than the left motor. (Found 75% with trial and error)
	 * 
	 * @param speed The speed to drive the robot at.
	 */
	public void straight(double speed) {
		if(canDrive())
			drive.tankDrive(speed, speed * 0.75);
	}
	
}
