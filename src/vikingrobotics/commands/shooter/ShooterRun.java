package vikingrobotics.commands.shooter;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import vikingrobotics.misc.Constants;
import vikingrobotics.misc.Debug;
import vikingrobotics.commands.CommandBase;

public class ShooterRun extends CommandBase implements Constants {

	private boolean hasFinished = false;
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
	
	public void hasFinished(boolean finished) {
		this.hasFinished = finished;
	}

	protected void initialize() {
		shooter.setSpeed(0.0);
	}

	protected void execute() {
		if(!hasSpeed) {
			if(oi.getDS().getDigitalIn(kDSDigitalInputShooterOnJ2)) {
				speed = oi.getJoystick2().getJoystickThrottle(kJoystick2AxisThrottle);
			}
			else if(oi.getDS().getDigitalIn(kDSDigitalInputShooterAI1)) {
				speed = oi.getDS().getAnalogIn(1) / 5;
			}
			else {
				speed = oi.getJoystick().getJoystickThrottle(kJoystickAxisThrottle);
			}
		}
		shooter.run(this.speed);
		SmartDashboard.putDouble("ShooterSpeed", this.speed);
	}

	protected boolean isFinished() {
		return isTimedOut() || hasFinished;
	}

	protected void end() {
		shooter.stop();
	}

	protected void interrupted() {
		Debug.println("[interrupted] " + getName());
		shooter.stop();
	}

}
