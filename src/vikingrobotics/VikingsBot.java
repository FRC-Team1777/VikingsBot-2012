/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package vikingrobotics;

import vikingrobotics.commands.CommandBase;
import vikingrobotics.commands.autonomous.Autonomous;
import vikingrobotics.misc.Constants;
import vikingrobotics.misc.Debug;
import vikingrobotics.misc.Utils;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 * 
 * @author Neal
 */
public class VikingsBot extends IterativeRobot implements Constants {
	
	private Command autonomousCommand;
	
	/**
	 * Robot-wide initialization code which will be called when the robot is first powered on.
	 * It will be called exactly 1 time.
	 */
	public void robotInit() {
		Debug.println("[robotInit] Initializing...");
		autonomousCommand = new Autonomous();
        // Initialize CommandBase so that everything is ready to run
		CommandBase.init();
		SmartDashboard.putData(Scheduler.getInstance());
		Debug.println("[robotInit] Initialization Done!");
	}
	
	/**
	 * Initialization code for autonomous mode which will be called each time
	 * the robot enters autonomous mode.
	 */
	public void autonomousInit() {
		CommandBase.oi.getDS().print(1, "Autonomous");
		Debug.println("[mode] Autonomous");
		autonomousCommand.start();
	}
	
	/**
	 * Periodic code for autonomous mode which will be called periodically at a regular
	 * rate while the robot is in autonomous mode.
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		updateDashboard();
	}
	
	/**
	 * Continuous code for autonomous mode which will be called repeatedly as frequently
	 * as possible while the robot is in autonomous mode.
	 */
	public void autonomousContinuous() {}
	
	/**
	 * Initialization code for teleop mode which will be called each time
	 * the robot enters teleop mode.
	 */
	public void teleopInit() {
		// This makes sure that the autonomous stops running
		// when teleop starts running.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		CommandBase.oi.getDS().print(1, "Operator control");
		Debug.println("[mode] Operator control");
	}
	
	/**
	 * Periodic code for teleop mode which will be called periodically at a regular
	 * rate while the robot is in teleop mode.
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		updateDashboard();
	}
	
	/**
	 * Continuous code for teleop mode which will be called repeatedly as frequently
	 * as possible while the robot is in teleop mode.
	 */
	public void teleopContinuous() {}
	
	/**
	 * Initialization code for disabled mode which will be called each time
	 * the robot enters disabled mode.
	 */
	public void disabledInit() {
		CommandBase.oi.getDS().print(1, "Disabled");
		Debug.println("[mode] Disabled");
	}
	
	/**
	 * Periodic code for disabled mode which will be called periodically at a regular
	 * rate while the robot is in disabled mode.
	 */
	public void disabledPeriodic() {
		updateDashboard();
	}
	
	/**
	 * Continuous code for disabled mode which will be called repeatedly as frequently
	 * as possible while the robot is in disabled mode.
	 */
	public void disabledContinuous() {}
		
	/**
	 * Update the SmartDashboard, DriverStation and the UserMessages from one place to avoid confusion.
	 */
	public void updateDashboard() {
		// Only update these if not in Disabled mode. (Not needed; save battery.)
		// Anything battery consuming (updates often) and not needed in Disabled should go here.
		if(!isDisabled()) {
			SmartDashboard.putDouble("Sonar", CommandBase.drivetrain.getSonarDistance());
		}
		// Update all these no matter what mode the robot is in.
		SmartDashboard.putDouble("Battery Percent", Utils.scaleBatteryVoltage(CommandBase.oi.getDS().getDS().getBatteryVoltage()));
		SmartDashboard.putDouble("Shooter Speed", CommandBase.shooter.getSpeed());
		SmartDashboard.putDouble("ShooterSpeed", CommandBase.shooter.getSpeed());
		SmartDashboard.putBoolean("SystemActive", isSystemActive());
		SmartDashboard.putBoolean("SensorExtracted", CommandBase.arm.getSensorExtracted());
		SmartDashboard.putBoolean("SensorRetracted", CommandBase.arm.getSensorRetracted());
		SmartDashboard.putBoolean("SensorLatch", CommandBase.arm.getSensorLatch());
		CommandBase.oi.getDS().getDS().setDigitalOut(kDSDigitalOutputSensorExtracted, CommandBase.arm.getSensorExtracted());
		CommandBase.oi.getDS().getDS().setDigitalOut(kDSDigitalOutputSensorRetracted, CommandBase.arm.getSensorRetracted());
		CommandBase.oi.getDS().getDS().setDigitalOut(kDSDigitalOutputSensorLatch, CommandBase.arm.getSensorLatch());
	}
	
}
