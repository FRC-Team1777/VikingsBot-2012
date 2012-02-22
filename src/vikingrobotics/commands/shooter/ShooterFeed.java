package vikingrobotics.commands.shooter;

import vikingrobotics.misc.Debug;
import vikingrobotics.commands.CommandBase;

public class ShooterFeed extends CommandBase {
	
	private boolean forceFeed = false;
	private boolean hasFinished = false;
	private double timeout;
	
	public ShooterFeed(double timeout) {
		this.timeout = timeout;
	}
	
	public ShooterFeed(double timeout, boolean forceFeed) {
		this(timeout);
		this.forceFeed = forceFeed;
	}

	protected void initialize() {
		Debug.print("[" + this.getName() + "] initialize");
		Debug.print("\tTimeout: " + timeout);
		Debug.print("\tForceFeed: " + forceFeed);
		setTimeout(timeout);
	}

	protected void execute() {
		if(shooter.getSpeed() > 0.2 || forceFeed) {
			shooter.setFeederForward();
		}
	}

	protected boolean isFinished() {
		return hasFinished || isTimedOut();
	}

	protected void end() {
		Debug.println("\t\tDONE");
		shooter.stopFeeder();
	}

	protected void interrupted() {
		Debug.println("[interrupted] " + getName());
		shooter.stopFeeder();
	}

}
