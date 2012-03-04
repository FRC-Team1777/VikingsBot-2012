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

public class ShooterRun extends CommandBase implements Constants {

	private boolean hasSpeed = false;
	private double speed = 0.0;
	
	public ShooterRun() {
		super("ShooterRun");
		requires(shooter);
	}
	
	public ShooterRun(double speed) {
		this();
		this.speed = speed;
		this.hasSpeed = true;
	}

	protected void initialize() {
		Debug.println("[" + this.getName() + "] Speed: " + this.speed);
		shooter.setSpeed(0.0);
	}

	protected void execute() {
		/*
		 * If we didn't call ShooterRun with speed, get speed from the joystick throttle.
		 */
		if (!hasSpeed) {
			/*
			 * If DigitalInput for ShooterOnJ2 on DS is on, get speed from Attack3 Throttle
			 */
			if(oi.getDS().getDS().getDigitalIn(kDSDigitalInputShooterOnJ2)) {
				speed = oi.getJoystick2().getJoystickThrottle(kJoystick2AxisThrottle);
			}
			/*
			 * If DigitalInput for ShooterOnJ2 on DS is off, get speed from Extreme 3D Throttle
			 */
			else {
				speed = oi.getJoystick().getJoystickThrottle(kJoystickAxisThrottle);
			}
		}
		shooter.run(this.speed);
	}

	protected boolean isFinished() {
		return isTimedOut();
	}

	protected void end() {
		Debug.println("[" + this.getName() + "] Stopped");
		shooter.stop();
	}

	protected void interrupted() {
		Debug.println("[interrupted] " + getName());
		shooter.stop();
	}

}
