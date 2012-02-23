/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package vikingrobotics.commands.arm;

import vikingrobotics.misc.Constants;
import vikingrobotics.misc.Debug;
import vikingrobotics.commands.CommandBase;

public class ArmManual extends CommandBase {
	
	public ArmManual() {
		super("ArmManual");
		requires(arm);
	}
	
	protected void initialize() {
	}

	protected void execute() {
		arm.setSpeed(oi.getGamePad().getAxis(Constants.kGamepadAxisTrigger));
		arm.extract();
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
		arm.stop();
	}

	protected void interrupted() {
		Debug.println("[interrupted] " + getName());
		arm.stop();
	}

}
