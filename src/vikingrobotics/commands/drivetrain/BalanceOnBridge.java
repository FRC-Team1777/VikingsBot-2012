package vikingrobotics.commands.drivetrain;

import vikingrobotics.commands.CommandBase;
import vikingrobotics.commands.delay;
import vikingrobotics.misc.Debug;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class BalanceOnBridge extends CommandGroup {
	
//	private boolean isBalanced = false;
	private static double direction = 0;
	private double driveTimeout = 0.5;
	
	public BalanceOnBridge(double gyroAngle) {
		super("BalanceOnBridge");
		if(!CommandBase.drivetrain.isBalanced()) {
			setDirection(gyroAngle);
			addSequential(new DriveStraight(0.65 * direction), driveTimeout);
			addSequential(new delay(), 1.0);
			addSequential(new CheckIfBalanced());
			if(CommandBase.drivetrain.isBalanced()) this.cancel();
		}
		if(CommandBase.drivetrain.isBalanced()) this.cancel();
	}
	
	public void setDirection(double gyroAngle) {
		if(gyroAngle < -2) {
			direction = -1;
		}
		else if(gyroAngle > 2) {
			direction = 1;
		}
		else {
			direction = 0;
		}
	}
	
}
