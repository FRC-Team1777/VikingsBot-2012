package vikingrobotics.commands.autonomous;

import vikingrobotics.misc.Debug;
import vikingrobotics.commands.CommandBase;

public class AutonBackUpToBridge extends CommandBase {
	
	public AutonBackUpToBridge() {
		super("AutonBackUpToBridge");
		requires(drivetrain);
	}

	protected void initialize() {
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {
		Debug.println("[interrupted] " + getName());
	}

}
