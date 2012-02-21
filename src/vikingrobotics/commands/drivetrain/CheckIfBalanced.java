package vikingrobotics.commands.drivetrain;

import vikingrobotics.misc.Debug;
import vikingrobotics.commands.CommandBase;

public class CheckIfBalanced extends CommandBase {
	
	private boolean isBalanced = false;
	private BalanceOnBridge balanceOnBridge;
	
	public CheckIfBalanced() {
		super("CheckIfBalanced");
	}

	protected void initialize() {
//		oi.getGyro().setSensitivity(0.007);
	}

	protected void execute() {
//		double gyroAngle = oi.getGyro().getAngle();
//		if(gyroAngle > -3 || gyroAngle < 3) {
//			this.isBalanced = true;
//		}
	}

	protected boolean isFinished() {
		return isBalanced;
	}

	protected void end() {
		drivetrain.setBalanced(isBalanced);
	}

	protected void interrupted() {
		Debug.println("[interrupted] " + getName());
	}

}
