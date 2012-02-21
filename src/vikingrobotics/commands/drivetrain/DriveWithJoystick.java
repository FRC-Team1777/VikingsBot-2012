package vikingrobotics.commands.drivetrain;

import vikingrobotics.misc.Constants;
import vikingrobotics.misc.Debug;
import vikingrobotics.commands.CommandBase;

public class DriveWithJoystick extends CommandBase implements Constants {

	public DriveWithJoystick() {
		super("DriveWithJoystick");
		requires(drivetrain);
	}

	protected void initialize() {}

	protected void execute() {
		if (oi.getDS().getDigitalIn(kDSDigitalInputArcadeDrive)) {
			drivetrain.arcadeDrive(oi.getJoystick().getAxis(kJoystickAxisY), oi.getJoystick().getAxis(kJoystickAxisX));
		}
		else {
			drivetrain.tankDrive(oi.getGamePad().getAxis(kGamepadAxisLeftStickY), oi.getGamePad().getAxis(kGamepadAxisRightStickY));
		}
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		drivetrain.stop();
	}

	protected void interrupted() {
		Debug.println("[interrupted] " + getName());
	}

}
