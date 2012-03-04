/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package vikingrobotics.commands.drivetrain;

import vikingrobotics.misc.Debug;
import vikingrobotics.commands.CommandBase;

public class DriveStraight extends CommandBase {
	
	private double timeout;
	private double speed;

	public DriveStraight(double speed, double timeout) {
		requires(drivetrain);
		this.speed = speed;
		this.timeout = timeout;
	}

	protected void initialize() {
		setTimeout(timeout);
		Debug.print("[" + this.getName() + "] Speed: " + this.speed);
		Debug.print("\tTimeout: " + timeout);
	}

	protected void execute() {
		drivetrain.straight(speed);
	}

	protected boolean isFinished() {
		return isTimedOut();
	}

	protected void end() {
		Debug.println("\t\tDONE");
		drivetrain.stop();
	}

	protected void interrupted() {
		Debug.println("\t[interrupted] " + getName());
		drivetrain.stop();
	}

}
