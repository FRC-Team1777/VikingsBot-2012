package vikingrobotics.commands.arm;

import edu.wpi.first.wpilibj.Timer;
import vikingrobotics.misc.Debug;
import vikingrobotics.subsystems.Arm;
import vikingrobotics.commands.CommandBase;

public class ArmSet extends CommandBase {
	
	private Timer ArmTimer = new Timer();;
	private double timeAfterSensor = 0.0;
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
		timeAfterSensor = 0.0;
		ArmTimer.reset();
		if(hasTimeout)
			setTimeout(timeout);
	}

	protected void execute() {
		arm.start();
		if(!arm.getSensorRetracted()) {
			timeAfterSensor++;
			ArmTimer.start();
		}
	}

	protected boolean isFinished() {
		return isTimedOut() || (ArmTimer.get() > 0.2 * 1e6);
	}

	protected void end() {
		ArmTimer.stop();
		Debug.println("[ArmSet] timeAfterSensor: " + timeAfterSensor);
		Debug.println("[ArmSet] ArmTimer: " + ArmTimer.get() * 1e6);
		Debug.println("[ArmSet] isTimedOut: " + isTimedOut());
		arm.stop();
	}

	protected void interrupted() {
		Debug.println("[interrupted] " + getName());
		ArmTimer.stop();
		arm.stop();
	}

}
