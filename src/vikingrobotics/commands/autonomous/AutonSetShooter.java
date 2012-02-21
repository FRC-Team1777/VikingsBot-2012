package vikingrobotics.commands.autonomous;

import vikingrobotics.misc.Debug;
import vikingrobotics.commands.CommandBase;

public class AutonSetShooter extends CommandBase {
	
	private boolean hasFinished = false;
	private boolean isTimeoutSet = false;
	
	public AutonSetShooter() {
		super("AutonSetShooter");
	}

	protected void initialize() {
		shooter.setSpeed(0.7); // find out the exact speed
		shooter.setAngle(45);
	}

	protected void execute() {
		shooter.run();
		shooter.updateAngle();
		if(shooter.isAtSetPoint()) {
			if(!isTimeoutSet) {
				isTimeoutSet = true;
				setTimeout(1);
			}
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
