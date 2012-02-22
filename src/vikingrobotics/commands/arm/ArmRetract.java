package vikingrobotics.commands.arm;

import vikingrobotics.misc.Debug;
import vikingrobotics.commands.CommandBase;

public class ArmRetract extends CommandBase {

	private boolean sensorInvalid = false;
	private boolean hasFinished = false;
	private boolean hasTimeout = false;
	private double speed = 0.0;
	private double timeout;
	
	public ArmRetract() {
		super("ArmRetract");
		requires(arm);
		this.speed = 0.8;
	}
	
	public ArmRetract(double timeout) {
		this();
		this.hasTimeout = true;
		this.timeout = timeout;
	}

	protected void initialize() {
		arm.setRetract();
		arm.setSpeed(this.speed);
		hasFinished = false;
		Debug.print("[ArmRetract] Speed: " + this.speed);
		if(hasTimeout) {
			Debug.print("\tTimeout: " + timeout);
			setTimeout(timeout);
		}
		Debug.print("\tSensorExtracted: " + arm.getSensorExtracted());
		Debug.println("\tSensorRetracted: " + arm.getSensorRetracted());
	}

	protected void execute() {
		arm.start();
		if (arm.getSensorRetracted())
			hasFinished = true;
	}

	protected boolean isFinished() {
		return isTimedOut() || hasFinished;
	}

	protected void end() {
		if(sensorInvalid) Debug.println("[error] Sensor invalid when trying to retract");
		arm.stop();
	}

	protected void interrupted() {
		Debug.println("[interrupted] " + getName());
		arm.stop();
	}

}
