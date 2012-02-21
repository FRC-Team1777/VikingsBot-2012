package vikingrobotics.commands.autonomous;

import vikingrobotics.misc.Debug;
import vikingrobotics.commands.CommandBase;

public class AutonShoot extends CommandBase {
	
	private boolean hasFinished = false;
	
	public AutonShoot() {
		super("AutonShoot");
		requires(shooter);
	}

	protected void initialize() {
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return hasFinished;
	}

	protected void end() {
	}

	protected void interrupted() {
		Debug.println("[interrupted] " + getName());
	}

}
