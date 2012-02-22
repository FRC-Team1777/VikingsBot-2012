package vikingrobotics.commands;

import vikingrobotics.misc.Debug;

public class delay extends CommandBase {

	private double timeout;

	public delay(double timeout) {
		this.timeout = timeout;
	}

	protected void initialize() {
		Debug.print("[delay] Timeout: " + timeout);
		setTimeout(timeout);
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return isTimedOut();
	}

	protected void end() {
		Debug.println("\t\tDONE");
	}

	protected void interrupted() {}

}
