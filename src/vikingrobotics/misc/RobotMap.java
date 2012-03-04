/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package vikingrobotics.misc;

/**
 * The ports for all motors on the robot.
 * @author Neal
 */
public class RobotMap {
	
	// Joysticks
	public static final int kJoystick1 = 1;					// Port 1
	public static final int kJoystick2 = 2;					// Port 2
	public static final int kJoystick3 = 3;					// Port 3
	public static final int kJoystick4 = 4;					// Port 4

	// Motors/sensors used by the arm
	public static final int kArmChannel = 5;				// PWM 5
	public static final int kArmLatchChannel = 7;			// PWM 7
	public static final int kArmSensorExtracted = 5;		// Digital IO 5
	public static final int kArmSensorRetracted = 2;		// Digital IO 2
	public static final int kArmSensorLatch = 3;			// Digital IO 3

	// Motor used by the ball grabber
	public static final int kGrabberChannel = 6;			// PWM 6
	
	// Motor used by the shooter
	public static final int kShooterFlyWheelChannel = 4;	// PWM 4
	public static final int kShooterFeederChannel = 3;		// PWM 3
	public static final int kShooterAnglerChannel = 1;		// Relay 1
	
	// Drive motors
	public static final int kDriveLeftMotor = 2;			// PWM 2
	public static final int kDriveRightMotor = 1;			// PWM 1

	// Sonar slot
	public static final int kSonarChannel = 2;				// Analog 2

	// Gyroscope slot
	public static final int kGyroChannel = 1;				// Analog 1

}
