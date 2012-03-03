/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package vikingrobotics.commands.arm;

import vikingrobotics.misc.Debug;
import vikingrobotics.commands.CommandBase;

public class ArmLatch extends CommandBase {

	private boolean hasFinished = false;
	private boolean hasTimeout = false;
	private double timeout;
	
	public ArmLatch() {
		super("ArmLatch");
		requires(arm);
	}
	
	public ArmLatch(double timeout) {
		this();
		this.hasTimeout = true;
		this.timeout = timeout;
	}

	protected void initialize() {
		hasFinished = false;
		Debug.print("[ArmLatch] initialize");
		if (hasTimeout) {
			Debug.print("\tTimeout: " + timeout);
			setTimeout(timeout);
		}
		Debug.print("\tTimeStarted: " + timeSinceInitialized());
		if (!arm.getSensorExtracted() && !oi.getDS().getDS().getDigitalIn(5)) {
			hasFinished = true;
			Debug.print("\t[ERROR] ARM NOT EXTRACTED!");
		}
	}

	protected void execute() {
		arm.latch();
		if (arm.getSensorLatch())
			hasFinished = true;
	}

	protected boolean isFinished() {
		return isTimedOut() || hasFinished;
	}

	protected void end() {
		Debug.println("\tTimeEnded: " + timeSinceInitialized());
		arm.stopLatch();
	}

	protected void interrupted() {
		Debug.print("\tTimeEnded: " + timeSinceInitialized());
		Debug.println("\t[interrupted] " + getName());
		arm.stopLatch();
	}

}
