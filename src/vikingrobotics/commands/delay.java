/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package vikingrobotics.commands;

import vikingrobotics.misc.Debug;

public class delay extends CommandBase {

	private double timeout;

	public delay(double timeout) {
		this.timeout = timeout;
	}

	protected void initialize() {
		Debug.print("[delay] Timeout: " + timeout);
		setTimeout(timeout);
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return isTimedOut();
	}

	protected void end() {
		Debug.println("\t\tDONE");
	}

	protected void interrupted() {}

}
