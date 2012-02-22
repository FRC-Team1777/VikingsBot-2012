package vikingrobotics.misc;

/**
 * The ports for all motors on the robot.
 * @author Neal
 */
public class RobotMap {
	
	// Joysticks
	public static final int kJoystick1 = 1;
	public static final int kJoystick2 = 2;
	public static final int kJoystick3 = 3;
	public static final int kJoystick4 = 4;

	// LED used by the camera
	public static final int kCamLED = 7; // UNKNOWN

	// Motor used by the arm
	public static final int kArmChannel = 5;
	public static final int kArmLatchChannel = 2; // Relay
	public static final int kArmSensorExtracted = 1;
	public static final int kArmSensorRetracted = 2;

	// Motor used by the ball grabber
	public static final int kGrabberChannel = 6;
	
	// Motor used by the shooter
	public static final int kShooterFlyWheelChannel = 4;
	public static final int kShooterFeederChannel = 3;
	public static final int kShooterAnglerChannel = 1; // Relay
	
	// Drive motors
	public static final int kDriveLeftMotor = 2;
	public static final int kDriveRightMotor = 1;
	
	// Encoders for drivetrain
	public static final int kEncoderLeftA = 1;
	public static final int kEncoderLeftB = 2;
	public static final int kEncoderRightA = 3;
	public static final int kEncoderRightB = 4;

	// Sonar slot
	public static final int kSonarChannel = 2;

	// Gyroscope slot
	public static final int kGyroChannel = 1;
	public static final int kGyro2Channel = 3;

}
