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
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem for the arm to bring the bridge down.
 * @author Neal
 */
public class Arm extends Subsystem implements Constants {

	private double speed = 0.0;
	private Jaguar arm, latcher;
	private DigitalInput sensorExtracted;
	private DigitalInput sensorRetracted;
	private DigitalInput sensorLatch;
	
	public Arm() {
		super("Arm");
		arm = new Jaguar(RobotMap.kArmChannel);
		latcher = new Jaguar(RobotMap.kArmLatchChannel);
		sensorExtracted = new DigitalInput(RobotMap.kArmSensorExtracted);
		sensorRetracted = new DigitalInput(RobotMap.kArmSensorRetracted);
		sensorLatch = new DigitalInput(RobotMap.kArmSensorLatch);
		Debug.println("[Arm] Initializing arm motor on channel " + RobotMap.kArmChannel);
		Debug.println("[Arm] Initializing arm latch motor on channel " + RobotMap.kArmLatchChannel);
		Debug.println("[Arm] Initializing arm sensor extracted on DIO " + RobotMap.kArmSensorExtracted);
		Debug.println("[Arm] Initializing arm sensor retracted on DIO " + RobotMap.kArmSensorRetracted);
		Debug.println("[Arm] Initializing arm sensor latch on DIO " + RobotMap.kArmSensorLatch);
	}
	
	public void initDefaultCommand() {}
	
	public void latch() {
		latcher.set(kArmLatchSpeed * -1);
	}
	
	public void unlatch() {
		latcher.set(kArmLatchSpeed * 1);
	}
	
	public void stopLatch() {
		latcher.set(0.0);
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
	
	public boolean getSensorLatch() {
		return sensorLatch.get();
	}
		
}
