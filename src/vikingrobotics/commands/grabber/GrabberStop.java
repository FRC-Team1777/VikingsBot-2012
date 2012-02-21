package vikingrobotics.commands.grabber;

import vikingrobotics.misc.Debug;
import vikingrobotics.commands.CommandBase;

public class GrabberStop extends CommandBase {
	
	private boolean hasFinished = false;
	
	public GrabberStop() {
		super("GrabberStop");
		requires(grabber);
	}

	protected void initialize() {
		hasFinished = false;
	}

	protected void execute() {
		grabber.stop();
		hasFinished = true;
	}

	protected boolean isFinished() {
		return hasFinished;
	}

	protected void end() {
		grabber.stop();
	}

	protected void interrupted() {
		Debug.println("[interrupted] " + getName());
		grabber.stop();
	}

}
