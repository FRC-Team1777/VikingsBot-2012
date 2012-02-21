package vikingrobotics.commands.shooter;

import vikingrobotics.misc.Debug;
import vikingrobotics.commands.CommandBase;

public class ShooterFeed extends CommandBase {
	
	private boolean forceFeed = false;
	private boolean hasFinished = false;
	private boolean hasTimeout = false;
	private double timeout;
	
	public ShooterFeed() {
	}
	
	public ShooterFeed(double timeout) {
		this();
		this.hasTimeout = true;
		this.timeout = timeout;
	}
	
	public ShooterFeed(double timeout, boolean forceFeed) {
		this(timeout);
		this.forceFeed = forceFeed;
	}

	protected void initialize() {
		if (hasTimeout)
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
		shooter.stopFeeder();
	}

	protected void interrupted() {
		Debug.println("[interrupted] " + getName());
		shooter.stopFeeder();
	}

}
