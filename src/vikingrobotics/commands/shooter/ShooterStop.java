/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package vikingrobotics.commands.shooter;

import vikingrobotics.misc.Debug;
import vikingrobotics.commands.CommandBase;

public class ShooterStop extends CommandBase {
	
	public ShooterStop() {
		super("ShooterStop");
		requires(shooter);
	}
	
	protected void initialize() {
		Debug.println("[" + this.getName() + "]");
	}

	protected void execute() {
		shooter.stop();
	}

	protected boolean isFinished() {
		return true; // This will call end() and will stop it anyways. Will also kill the thread that way.
	}

	protected void end() {
		shooter.stop();
	}

	protected void interrupted() {
		Debug.println("[interrupted] " + getName());
		shooter.stop();
	}

}
