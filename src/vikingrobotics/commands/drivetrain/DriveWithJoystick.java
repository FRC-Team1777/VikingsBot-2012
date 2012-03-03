/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package vikingrobotics.commands.drivetrain;

import vikingrobotics.misc.Constants;
import vikingrobotics.misc.Debug;
import vikingrobotics.commands.CommandBase;

public class DriveWithJoystick extends CommandBase implements Constants {

	public DriveWithJoystick() {
		super("DriveWithJoystick");
		requires(drivetrain);
	}

	protected void initialize() {
		Debug.println("[" + this.getName() + "] initialized");
	}

	protected void execute() {
		if (oi.getDS().getDS().getDigitalIn(kDSDigitalInputArcadeDrive)) {
			drivetrain.arcadeDrive(oi.getJoystick().getAxis(kJoystickAxisY), oi.getJoystick().getAxis(kJoystickAxisX));
		}
		else {
			if (oi.getDS().getDS().getDigitalIn(kDSDigitalInputSlowDrive)) {
				drivetrain.tankDrive(oi.getGamePad().getAxis(kGamepadAxisLeftStickY) * 0.8, oi.getGamePad().getAxis(kGamepadAxisRightStickY) * 0.8);
			}
			else {
				drivetrain.tankDrive(oi.getGamePad().getAxis(kGamepadAxisLeftStickY), oi.getGamePad().getAxis(kGamepadAxisRightStickY));
			}
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
