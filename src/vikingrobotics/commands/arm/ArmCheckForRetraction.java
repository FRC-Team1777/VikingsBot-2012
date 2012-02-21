package vikingrobotics.commands.arm;

import vikingrobotics.commands.CommandBase;
import vikingrobotics.misc.Debug;

public class ArmCheckForRetraction extends CommandBase {
	
	private boolean hasFinished = false;
	private boolean hasTimeout = false;
	private double timeout;
	
	public ArmCheckForRetraction() {
		super("ArmCheckForRetraction");
	}
	
	public ArmCheckForRetraction(double timeout) {
		this();
		this.hasTimeout = true;
		this.timeout = timeout;
	}

	protected void initialize() {
		hasFinished = false;
		if (hasTimeout)
			setTimeout(timeout);
	}

	protected void execute() {
//		double gyroAngle = oi.getGyro().getAngle();
//		if (gyroAngle > 10 || gyroAngle < -10) {
//			hasFinished = true;
//		}
	}

	protected boolean isFinished() {
		return isTimedOut() || hasFinished;
	}

	protected void end() {
	}

	protected void interrupted() {
		Debug.println("[interrupted] " + getName());
	}

}
