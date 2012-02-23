/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package vikingrobotics.commands.shooter;

import vikingrobotics.misc.Constants;
import vikingrobotics.misc.Debug;
import vikingrobotics.commands.CommandBase;

public class ShooterShoot extends CommandBase implements Constants {

	private boolean hasTimeout = false;
	private double timeout;
	
	public ShooterShoot() {
		super("ShooterShoot");
		requires(shooter);
	}
	
	public ShooterShoot(double timeout) {
		this();
		this.hasTimeout = true;
		this.timeout = timeout;
	}
	
	/**
	 * Given a distance that we want to shoot the ball, calculate
	 * the flywheel RPM necessary to shoot the ball that distance
	 */
	public static double desiredExitRPM(double distanceInches) {
		double g = 387; // gravity (inches per second squared) == 9.8 m/s^2
		double h = kHoopHeightTop - kShooterHeight; // difference in shooter and hoop height
		double thetaRadians = Math.toRadians(shooter.getGyroAngle());
		double linearSpeedInchesPerSecond = (distanceInches * Math.sqrt(g)) /
				(Math.sqrt(2) * Math.cos(thetaRadians) * Math.sqrt(distanceInches * Math.tan(thetaRadians) - h));
		double RPM = 60 * linearSpeedInchesPerSecond / kWheelCircumference;
		return RPM;
		// m * m/s^2  /  ( sqrt(2) * cos() * sqrt(m * tan() - h) )
	}


	protected void initialize() {
		if (hasTimeout)
			setTimeout(timeout);
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return isTimedOut();
	}

	protected void end() {
		shooter.stop();
	}

	protected void interrupted() {
		Debug.println("[interrupted] " + getName());
	}

}
