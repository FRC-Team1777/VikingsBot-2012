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
		arm.setExtract();
	}

	protected void execute() {
		arm.setSpeed(oi.getGamePad().getAxis(Constants.kGamepadAxisTrigger));
		arm.start();
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
