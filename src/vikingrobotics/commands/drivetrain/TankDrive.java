/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package vikingrobotics.commands.drivetrain;

import vikingrobotics.misc.Debug;
import vikingrobotics.commands.CommandBase;

public class TankDrive extends CommandBase {
	
	private double leftVal, rightVal;
	private double timeout;

	public TankDrive(double leftValue, double rightValue, double timeout) {
		requires(drivetrain);
		this.leftVal = leftValue;
		this.rightVal = rightValue;
		this.timeout = timeout;
	}

	protected void initialize() {
		setTimeout(timeout);
		Debug.print("[" + this.getName() + "] Left: " + leftVal);
		Debug.print("\tRight: " + rightVal);
		Debug.print("\tTimeout: " + timeout);
	}

	protected void execute() {
		drivetrain.tankDrive(leftVal, rightVal);
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
