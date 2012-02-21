/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package vikingrobotics;

import vikingrobotics.commands.arm.ArmRun;
import vikingrobotics.commands.autonomous.*;
import vikingrobotics.commands.shooter.ShooterMove;
import vikingrobotics.commands.CommandBase;
import vikingrobotics.misc.Constants;
import vikingrobotics.misc.Debug;
import vikingrobotics.misc.Utils;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * @author Neal
 */
public class CommandBasedRobot extends IterativeRobot implements Constants {
	
	private Command autonomousCommand;
	private boolean firstTime = true;

	public void robotInit() {
		autonomousCommand = new Auton1();
		CommandBase.init();
		SmartDashboard.putData(Scheduler.getInstance());
	}

	public void autonomousInit() {
		Debug.println("[mode] Autonomous");
		autonomousCommand.start();
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}
	
	public void autonomousContinuous() {}

	public void teleopInit() {
		Debug.println("[mode] Operator control");
		if (autonomousCommand != null)
			autonomousCommand.cancel();
//		SmartDashboard.putDouble("TestDouble", 5.1);
	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		updateDashboard();
		if(CommandBase.oi.getGamePad().getButton(11)) Debug.println("[gamepad] Button 11 pressed");
		if(CommandBase.oi.getGamePad().getButton(12)) Debug.println("[gamepad] Button 12 pressed");
		
//		CommandBase.oi.getCameraVision().doCamera();
	}
	
	public void teleopContinuous() {}
	
	public void disabledInit() {
		Debug.println("[mode] Disabled");
		if(firstTime) {
			CommandBase.oi.getUM().write(1, "Robot Ready!");
			firstTime = false;
		}
	}

	public void disabledPeriodic() {}
	
	public void disabledContinuous() {}
	
	public void updateDashboard() {
		SmartDashboard.putDouble("Battery Percent", CommandBase.oi.getDS().getBatteryVoltage() * 100 / kMaxBatteryVoltage);
		SmartDashboard.putDouble("Sonar", CommandBase.drivetrain.getSonarDistance());
		SmartDashboard.putDouble("Gyro", CommandBase.shooter.getGyroAngle());
		SmartDashboard.putDouble("Shooter Speed", CommandBase.shooter.getSpeed());
		SmartDashboard.putDouble("ShooterSpeed", CommandBase.shooter.getActualSpeed());
		SmartDashboard.putBoolean("SensorExtracted", CommandBase.arm.getSensorExtracted());
		SmartDashboard.putBoolean("SensorRetracted", CommandBase.arm.getSensorRetracted());
//		CommandBase.oi.getUM().write(2, "2: " + CommandBase.shooter.getSpeed());
//		CommandBase.oi.getUM().write(3, "3: " + CommandBase.oi.getDS().getAnalogIn(1));
		CommandBase.oi.getUM().write(4, "4: " + (Math.ceil(CommandBase.shooter.getGyroAngle() * 1000) / 1000));
//		CommandBase.oi.getUM().write(5, "Ex: " + CommandBase.arm.getSensorRetracted() + " | " + SmartDashboard.getDouble("TestDouble", 0.1));
//		CommandBase.oi.getUM().write(6, "Re: " + new ArmRun().isRunning());
		//CommandBase.drivetrain.printEncoders(5);
	}
	
}
