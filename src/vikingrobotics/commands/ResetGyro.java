/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package vikingrobotics.commands;

import vikingrobotics.misc.Debug;

public class ResetGyro extends CommandBase {

	private boolean hasFinished = false;
	
	public ResetGyro() {
		super("ResetGyro");
	}

	protected void initialize() {
	}

	protected void execute() {
		shooter.resetCurrentGyroAngle();
		hasFinished = true;
	}

	protected boolean isFinished() {
		return hasFinished;
	}

	protected void end() {}

	protected void interrupted() {
		Debug.println("[interrupted] " + getName());
	}

}
