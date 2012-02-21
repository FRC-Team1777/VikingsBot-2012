package vikingrobotics.commands;

import vikingrobotics.misc.Debug;

public class ResetGyro extends CommandBase {

	private boolean hasFinished = false;
	
	public ResetGyro() {
		super("ResetGyro");
	}

	protected void initialize() {
	}

	protected void execute() {
		shooter.resetCurrentGyroAngle();
		hasFinished = true;
	}

	protected boolean isFinished() {
		return hasFinished;
	}

	protected void end() {}

	protected void interrupted() {
		Debug.println("[interrupted] " + getName());
	}

}
