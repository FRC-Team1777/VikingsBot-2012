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

public class ShooterMove extends CommandBase {
	
	private boolean hasTimeout = false;
	private boolean hasSpeed = false;
	private double timeout;
	private double speed = 0.0;
	public static class Direction {
		public final int value;
		public static final Direction kStop = new Direction(0);
		public static final Direction kUp = new Direction(1);
		public static final Direction kDown = new Direction(-1);
		private Direction(int value) {
			this.value = value;
		}
	}
	
	public ShooterMove() {
		super("ShooterMove");
	}
	
	public ShooterMove(double speed) {
		this();
		this.speed = speed;
		this.hasSpeed = true;
	}
	
	public ShooterMove(double speed, double timeout) {
		this(speed);
		this.timeout = timeout;
		this.hasTimeout = true;
	}
	
	protected void initialize() {
		if(hasTimeout)
			setTimeout(timeout);
	}

	protected void execute() {
		if(!hasSpeed) {
			speed = oi.getGamePad().getAxis(Constants.kGamepadAxisDpadX);
		}
		if(speed > 0) {
			shooter.moveUp();
			Debug.println("[ShooterMove] moving up");
		}
		else if(speed < 0) {
			shooter.moveDown();
			Debug.println("[ShooterMove] moving down");
		}
		else {
			shooter.moveStop();
		}
	}
	
	protected boolean isFinished() {
		return isTimedOut();
	}

	protected void end() {
		shooter.moveStop();
	}

	protected void interrupted() {
		Debug.println("[interrupted] " + getName());
		shooter.moveStop();
	}

}
