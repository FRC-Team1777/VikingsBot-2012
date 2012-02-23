/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package vikingrobotics.commands.autonomous;

import vikingrobotics.misc.Debug;
import vikingrobotics.commands.CommandBase;

public class AutonShoot extends CommandBase {
	
	private boolean hasFinished = false;
	
	public AutonShoot() {
		super("AutonShoot");
		requires(shooter);
	}

	protected void initialize() {
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return hasFinished;
	}

	protected void end() {
	}

	protected void interrupted() {
		Debug.println("[interrupted] " + getName());
	}

}
