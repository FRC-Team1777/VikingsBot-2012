package vikingrobotics.commands.grabber;

import vikingrobotics.misc.Debug;
import vikingrobotics.commands.CommandBase;

public class GrabberReverse extends CommandBase {
	
	private boolean hasTimeout = false;
	private double timeout;
	
	public GrabberReverse() {
		super("GrabberReverse");
		requires(grabber);
	}
	
	public GrabberReverse(double timeout) {
		this();
		this.hasTimeout = true;
		this.timeout = timeout;
	}

	protected void initialize() {
	}

	protected void execute() {
		grabber.reverse();
	}

	protected boolean isFinished() {
		return isTimedOut();
	}

	protected void end() {
		grabber.stop();
	}

	protected void interrupted() {
		Debug.println("[interrupted] " + getName());
		grabber.stop();
	}

}
