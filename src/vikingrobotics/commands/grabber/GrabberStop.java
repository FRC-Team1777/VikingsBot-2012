/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package vikingrobotics.commands.grabber;

import vikingrobotics.misc.Debug;
import vikingrobotics.commands.CommandBase;

public class GrabberStop extends CommandBase {
	
	private boolean hasFinished = false;
	
	public GrabberStop() {
		super("GrabberStop");
		requires(grabber);
	}

	protected void initialize() {
		Debug.print("[" + this.getName() + "]");
		hasFinished = false;
	}

	protected void execute() {
		grabber.stop();
		hasFinished = true;
	}

	protected boolean isFinished() {
		return hasFinished;
	}

	protected void end() {
		Debug.println("\t\tDONE");
		grabber.stop();
	}

	protected void interrupted() {
		Debug.println("[interrupted] " + getName());
		grabber.stop();
	}

}
