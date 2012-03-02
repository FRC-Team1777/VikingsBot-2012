/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

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
		Debug.println("[Grabber] Initializing ball grabber jaguar on channel " + RobotMap.kGrabberChannel);
		grabberJag = new Jaguar(RobotMap.kGrabberChannel);
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
