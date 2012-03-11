/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package vikingrobotics.commands.arm;

import vikingrobotics.misc.Debug;
import vikingrobotics.misc.Utils;
import vikingrobotics.commands.CommandBase;

public class ArmUnlatch extends CommandBase {

	private boolean hasFinished = false;
	private double timeout;
	
	public ArmUnlatch(double timeout) {
		super("ArmUnlatch");
		requires(arm);
		this.timeout = timeout;
	}

	protected void initialize() {
		hasFinished = false;
		setTimeout(timeout);
		Debug.print("[" + this.getName() + "] Timeout: " + timeout);
		Debug.print("\tTimeStarted: " + Utils.roundDecimals(timeSinceInitialized(), 5));
		if (!arm.getSensorExtracted()) {
			hasFinished = true;
			Debug.print("\t[ERROR] ARM NOT EXTRACTED!");
		}
	}

	protected void execute() {
		arm.unlatch();
	}

	protected boolean isFinished() {
		return isTimedOut() || hasFinished;
	}

	protected void end() {
		Debug.print("\tSensorLatch: " + arm.getSensorLatch());
		Debug.println("\tTimeEnded: " + Utils.roundDecimals(timeSinceInitialized(), 5));
		arm.stopLatch();
	}

	protected void interrupted() {
		Debug.print("\tSensorLatch: " + arm.getSensorLatch());
		Debug.print("\tTimeEnded: " + Utils.roundDecimals(timeSinceInitialized(), 5));
		Debug.println("\t[interrupted] " + getName());
		arm.stopLatch();
	}

}
