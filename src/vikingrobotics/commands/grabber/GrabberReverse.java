/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package vikingrobotics.commands.grabber;

import vikingrobotics.misc.Debug;
import vikingrobotics.commands.CommandBase;

public class GrabberReverse extends CommandBase {
	
	private boolean hasTimeout = false;
	private double timeout;
	
	public GrabberReverse() {
		super("GrabberReverse");
		requires(grabber);
	}
	
	public GrabberReverse(double timeout) {
		this();
		this.hasTimeout = true;
		this.timeout = timeout;
	}

	protected void initialize() {
		Debug.print("[" + this.getName() + "] initialize");
		if (hasTimeout) {
			Debug.print("\tTimeout: " + timeout);
			setTimeout(timeout);
		}
	}

	protected void execute() {
		grabber.reverse();
	}

	protected boolean isFinished() {
		return isTimedOut();
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
