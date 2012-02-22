package vikingrobotics.commands.arm;

import vikingrobotics.misc.Debug;
import vikingrobotics.commands.CommandBase;

public class ArmExtract extends CommandBase {

	private boolean sensorInvalid = false;
	private boolean hasFinished = false;
	private boolean hasTimeout = false;
	private double speed = 0.0;
	private double timeout;
	
	public ArmExtract() {
		super("ArmExtract");
		requires(arm);
		this.speed = 0.8;
	}
	
	public ArmExtract(double timeout) {
		this();
		this.hasTimeout = true;
		this.timeout = timeout;
	}
	
	public ArmExtract(double timeout, boolean slow) {
		this(timeout);
		this.speed = 0.3;
	}

	protected void initialize() {
		arm.setExtract();
		arm.setSpeed(this.speed);
		hasFinished = false;
		if (arm.getSensorExtracted()) {
			sensorInvalid = true;
			hasFinished = true;
		}
		Debug.print("[ArmExtract] Speed: " + this.speed);
		if(hasTimeout) {
			Debug.print("\tTimeout: " + timeout);
			setTimeout(timeout);
		}
		Debug.print("\tSensorExtracted: " + arm.getSensorExtracted());
		Debug.println("\tSensorRetracted: " + arm.getSensorRetracted());
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
