/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package vikingrobotics.commands.arm;

import vikingrobotics.misc.Constants;
import vikingrobotics.misc.Debug;
import vikingrobotics.commands.CommandBase;

public class ArmRetract extends CommandBase implements Constants {

	private boolean sensorInvalid = false;
	private boolean hasFinished = false;
	private double speed = 0.0;
	private double timeout;
	
	public ArmRetract(double speed, double timeout) {
		super("ArmRetract");
		requires(arm);
		this.speed = speed;
		this.timeout = timeout;
	}

	protected void initialize() {
		arm.setSpeed(this.speed);
		hasFinished = false;
		setTimeout(timeout);
		Debug.print("[" + this.getName() + "] Speed: " + this.speed);
		Debug.print("\tTimeout: " + timeout);
		Debug.print("\tSensorExtracted: " + arm.getSensorExtracted());
		Debug.println("\tSensorRetracted: " + arm.getSensorRetracted());
	}

	protected void execute() {
		arm.retract();
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
