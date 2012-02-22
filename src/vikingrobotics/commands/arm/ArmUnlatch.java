package vikingrobotics.commands.arm;

import vikingrobotics.misc.Debug;
import vikingrobotics.commands.CommandBase;

public class ArmUnlatch extends CommandBase {

	private boolean hasFinished = false;
	private boolean hasTimeout = false;
	private double timeout;
	
	public ArmUnlatch() {
		super("ArmUnlatch");
		requires(arm);
	}
	
	public ArmUnlatch(double timeout) {
		this();
		this.hasTimeout = true;
		this.timeout = timeout;
	}

	protected void initialize() {
		hasFinished = false;
		Debug.print("[ArmUnlatch] initialize");
		if (hasTimeout) {
			Debug.print("\tTimeout: " + timeout);
			setTimeout(timeout);
		}
		Debug.print("\tTimeStarted: " + timeSinceInitialized());
	}

	protected void execute() {
		arm.unlatch();
	}

	protected boolean isFinished() {
		return isTimedOut() || hasFinished;
	}

	protected void end() {
		Debug.println("\tTimeEnded: " + timeSinceInitialized());
		arm.stopLatch();
	}

	protected void interrupted() {
		Debug.println("[interrupted] " + getName());
		arm.stopLatch();
	}

}
