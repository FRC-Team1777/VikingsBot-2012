/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package vikingrobotics.commands.shooter;

import vikingrobotics.commands.CommandBase;
import vikingrobotics.misc.Debug;

public class ResetGyro extends CommandBase {
	
	public ResetGyro() {
		super("ResetGyro");
	}

	protected void initialize() {
		shooter.resetCurrentGyroAngle();
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {}

	protected void interrupted() {
		Debug.println("[interrupted] " + getName());
	}

}
