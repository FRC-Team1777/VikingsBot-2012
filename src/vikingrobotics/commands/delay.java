package vikingrobotics.commands;

public class delay extends CommandBase {

	private boolean hasTimeout = false;
	private double timeout;

	public delay() {}

	public delay(double timeout) {
		this();
		this.hasTimeout = true;
		this.timeout = timeout;
	}

	protected void initialize() {
		if (hasTimeout)
			setTimeout(timeout);
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return isTimedOut();
	}

	protected void end() {}

	protected void interrupted() {}

}
