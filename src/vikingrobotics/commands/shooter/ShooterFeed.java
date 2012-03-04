/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package vikingrobotics.commands.shooter;

import vikingrobotics.misc.Debug;
import vikingrobotics.commands.CommandBase;

public class ShooterFeed extends CommandBase {
	
	private boolean forceFeed = false;
	private double timeout;
	
	public ShooterFeed(double timeout) {
		this.timeout = timeout;
	}
	
	public ShooterFeed(double timeout, boolean forceFeed) {
		this(timeout);
		this.forceFeed = forceFeed;
	}

	protected void initialize() {
		Debug.print("[" + this.getName() + "] initialize");
		Debug.print("\tTimeout: " + timeout);
		Debug.print("\tForceFeed: " + forceFeed);
		setTimeout(timeout);
	}

	protected void execute() {
		if(shooter.getSpeed() > 0.2 || forceFeed) {
			shooter.setFeederForward();
		}
		else {
			Debug.print("\t[ERROR]: Shooter not fast enough! " + shooter.getSpeed());
		}
	}

	protected boolean isFinished() {
		return isTimedOut();
	}

	protected void end() {
		Debug.println("\t\tDONE");
		shooter.stopFeeder();
	}

	protected void interrupted() {
		Debug.println("[interrupted] " + getName());
		shooter.stopFeeder();
	}

}
