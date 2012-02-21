package vikingrobotics.subsystems;

import vikingrobotics.misc.Constants;
import vikingrobotics.misc.Debug;
import vikingrobotics.misc.RobotMap;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem for the ball grabber.
 * @author Neal
 */
public class Grabber extends Subsystem implements Constants {

	private Jaguar grabberJag;
	
	public Grabber() {
		super("Grabber");
		grabberJag = new Jaguar(RobotMap.kGrabberChannel);
		Debug.println("[robot] Initializing ball grabber motor on channel " + RobotMap.kGrabberChannel);
	}
	
	protected void initDefaultCommand() {
	}
	
	public double getSpeed() {
		return grabberJag.get();
	}
	
	public void start() {
		grabberJag.set(1.0);
	}
	
	public void reverse() {
		grabberJag.set(-1.0);
	}
	
	public void stop() {
		grabberJag.set(0.0);
	}

}
