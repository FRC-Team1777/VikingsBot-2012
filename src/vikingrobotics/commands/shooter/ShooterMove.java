package vikingrobotics.commands.shooter;

import vikingrobotics.misc.Constants;
import vikingrobotics.misc.Debug;
import vikingrobotics.commands.CommandBase;

public class ShooterMove extends CommandBase {
	
	private boolean hasFinished = false;
	private boolean hasSpeed = false;
	private boolean firstTimeStopped = false;
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
	
	protected void initialize() {
		hasFinished = false;
		shooter.resetGyro();
	}

	protected void execute() {
		if(!hasSpeed) {
			speed = oi.getGamePad().getAxis(Constants.kGamepadAxisDpadX);
		}
		if(speed > 0 && canMove()) {
			shooter.moveUp();
			firstTimeStopped = true;
			Debug.println("[ShooterMove] moving up");
		}
		else if(speed < 0 && canMove()) {
			shooter.moveDown();
			firstTimeStopped = true;
			Debug.println("[ShooterMove] moving down");
		}
		else {
			shooter.moveStop();
			shooter.resetGyro();
			if (firstTimeStopped) {
				firstTimeStopped = false;
				Debug.println("[ShooterMove] changing angle");
				shooter.changingAngle();
			}
		}
	}
	
	public boolean canMove() {
		return true;
		//return (shooter.getGyroAngle() < 55 && shooter.getGyroAngle() > 25);
	}
	
	protected boolean isFinished() {
		return hasFinished;
	}

	protected void end() {
		shooter.moveStop();
		shooter.changingAngle();
	}

	protected void interrupted() {
		Debug.println("[interrupted] " + getName());
		shooter.moveStop();
		shooter.changingAngle();
	}

}
