package vikingrobotics.subsystems;

import vikingrobotics.misc.Debug;
import vikingrobotics.misc.RobotMap;
import vikingrobotics.commands.arm.ArmManual;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem for the arm to bring the bridge down.
 * @author Neal
 */
public class Arm extends Subsystem {

	private static double direction = 1;
	private double speed = 0.1;
	private Jaguar arm;
	private Relay latcher;
	private DigitalInput sensorExtracted, sensorRetracted;
	
	public Arm() {
		super("Arm");
		arm = new Jaguar(RobotMap.kArmChannel);
//		latcher = new Relay(RobotMap.kArmLatchChannel);
		Debug.println("[robot] Initializing arm motor on channel " + RobotMap.kArmChannel);
		sensorExtracted = new DigitalInput(1);
		sensorRetracted = new DigitalInput(2);
	}
	
	public void initDefaultCommand() {
//		setDefaultCommand(new ArmManual());
	}
	
	public void latch() {
//		latcher.set(Relay.Value.kForward);
	}
	
	public void unlatch() {
//		latcher.set(Relay.Value.kReverse);
	}
	
	public double getSpeed() {
		return this.speed;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public void setExtract() {
		direction = -1;
	}
	
	public void setRetract() {
		direction = 1;
	}
	
	public void start() {
		arm.set(this.speed * direction);
	}
	
	public void stop() {
		arm.set(0.0);
	}
	
	public boolean getSensorRetracted() {
		return !sensorRetracted.get();
	}
	
	public boolean getSensorExtracted() {
		return !sensorExtracted.get();
	}
		
}
