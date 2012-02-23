/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

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

	private double speed = 0.1;
	private Jaguar arm;
	private Relay latcher;
	private DigitalInput sensorExtracted, sensorRetracted;
	
	public Arm() {
		super("Arm");
		arm = new Jaguar(RobotMap.kArmChannel);
//		latcher = new Relay(RobotMap.kArmLatchChannel);
		Debug.println("[robot] Initializing arm motor on channel " + RobotMap.kArmChannel);
//		Debug.println("[robot] Initializing arm latch motor on relay " + RobotMap.kArmLatchChannel);
		sensorExtracted = new DigitalInput(RobotMap.kArmSensorExtracted);
		sensorRetracted = new DigitalInput(RobotMap.kArmSensorRetracted);
	}
	
	public void initDefaultCommand() {
		// Don't want to do this unless emergency/testing
//		setDefaultCommand(new ArmManual());
	}
	
	public void latch() {
//		latcher.set(Relay.Value.kForward);
	}
	
	public void unlatch() {
//		latcher.set(Relay.Value.kReverse);
	}
	
	public void stopLatch() {
//		latcher.set(Relay.Value.kOff);
	}
	
	public double getSpeed() {
		return this.speed;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public void extract() {
		arm.set(this.speed * -1);
	}
	
	public void retract() {
		arm.set(this.speed * 1);
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
