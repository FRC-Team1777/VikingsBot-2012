package vikingrobotics.commands.arm;

import vikingrobotics.misc.Debug;
import vikingrobotics.commands.CommandBase;

public class ArmExtract extends CommandBase {

	private boolean sensorInvalid = false;
	private boolean hasFinished = false;
	private boolean hasTimeout = false;
	private double timeout;
	
	public ArmExtract() {
		super("ArmExtract");
		requires(arm);
	}
	
	public ArmExtract(double timeout) {
		this();
		this.hasTimeout = true;
		this.timeout = timeout;
	}

	protected void initialize() {
		arm.setExtract();
		arm.setSpeed(0.8);
		hasFinished = false;
		if (arm.getSensorRetracted() || arm.getSensorExtracted()) {
			sensorInvalid = true;
			hasFinished = true;
		}
		if(hasTimeout)
			setTimeout(timeout);
	}

	protected void execute() {
		arm.start();
		if (arm.getSensorExtracted())
			hasFinished = true;
	}

	protected boolean isFinished() {
		return isTimedOut() || hasFinished;
	}

	protected void end() {
		if(sensorInvalid) Debug.println("[error] Sensor invalid when trying to extract");
		arm.stop();
	}

	protected void interrupted() {
		Debug.println("[interrupted] " + getName());
		arm.stop();
	}

}
