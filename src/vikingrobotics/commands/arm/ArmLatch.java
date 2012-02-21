package vikingrobotics.commands.arm;

import vikingrobotics.misc.Debug;
import vikingrobotics.commands.CommandBase;

public class ArmLatch extends CommandBase {

	private boolean hasFinished = false;
	private boolean hasTimeout = false;
	private double timeout;
	
	public ArmLatch() {
		super("ArmLatch");
		requires(arm);
	}
	
	public ArmLatch(double timeout) {
		this();
		this.hasTimeout = true;
		this.timeout = timeout;
	}

	protected void initialize() {
		hasFinished = false;
	}

	protected void execute() {
		arm.latch();
	}

	protected boolean isFinished() {
		return isTimedOut() || hasFinished;
	}

	protected void end() {
		arm.stopLatch();
	}

	protected void interrupted() {
		Debug.println("[interrupted] " + getName());
		arm.stopLatch();
	}

}
