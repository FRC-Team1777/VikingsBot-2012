package vikingrobotics.commands.arm;

import edu.wpi.first.wpilibj.Timer;
import vikingrobotics.misc.Debug;
import vikingrobotics.subsystems.Arm;
import vikingrobotics.commands.CommandBase;

public class ArmSet extends CommandBase {
	
	private boolean hasTimeout = false;
	private double timeout;
	
	public ArmSet() {
		super("ArmSet");
		requires(arm);
	}
	
	public ArmSet(double timeout) {
		this();
		this.hasTimeout = true;
		this.timeout = timeout;
	}

	protected void initialize() {
		arm.setExtract();
		arm.setSpeed(0.3);
		if(hasTimeout)
			setTimeout(timeout);
	}

	protected void execute() {
		arm.start();
	}

	protected boolean isFinished() {
		return isTimedOut();
	}

	protected void end() {
		arm.stop();
	}

	protected void interrupted() {
		Debug.println("[interrupted] " + getName());
		arm.stop();
	}

}
