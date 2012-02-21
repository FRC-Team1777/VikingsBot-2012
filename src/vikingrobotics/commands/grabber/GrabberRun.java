package vikingrobotics.commands.grabber;

import vikingrobotics.misc.Debug;
import vikingrobotics.commands.CommandBase;

public class GrabberRun extends CommandBase {
	
	private boolean hasTimeout = false;
	private double timeout;
	
	public GrabberRun() {
		super("GrabberRun");
		requires(grabber);
	}
	
	public GrabberRun(double timeout) {
		this();
		this.hasTimeout = true;
		this.timeout = timeout;
	}

	protected void initialize() {
	}

	protected void execute() {
		grabber.start();
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
