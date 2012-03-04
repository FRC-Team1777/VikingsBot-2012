/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package vikingrobotics;

import vikingrobotics.commands.arm.ArmExtract;
import vikingrobotics.commands.arm.ArmLatch;
import vikingrobotics.commands.arm.ArmRetract;
import vikingrobotics.commands.arm.ArmRun;
import vikingrobotics.commands.arm.ArmUnlatch;
import vikingrobotics.commands.grabber.GrabberReverse;
import vikingrobotics.commands.grabber.GrabberRun;
import vikingrobotics.commands.grabber.GrabberStop;
import vikingrobotics.commands.shooter.ShooterFeed;
import vikingrobotics.commands.shooter.ShooterMove;
import vikingrobotics.commands.shooter.ShooterRun;
import vikingrobotics.commands.shooter.ShooterStop;
import vikingrobotics.misc.Constants;
import vikingrobotics.misc.Debug;
import vikingrobotics.misc.Driverstation;
import vikingrobotics.misc.Gamepad;
import vikingrobotics.misc.RobotMap;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.buttons.InternalButton;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OI implements Constants {
	
	private static OI instance = null;
	
	private Gamepad gamepad;   // port 1
	private Gamepad joystick;  // port 2
	private Gamepad joystick2; // port 3
	private Driverstation m_ds;
	private SmartDashboard smartDashboard;
	
	// Non-physical buttons used with SmartDashboard
	private InternalButton buttonShooterUp = new InternalButton();
	private InternalButton buttonShooterDn = new InternalButton();
	private InternalButton buttonArmRun = new InternalButton();
	private InternalButton buttonFeedBall = new InternalButton();
	private InternalButton buttonGrabberRun = new InternalButton();
	private InternalButton buttonGrabberStop = new InternalButton();
	private InternalButton buttonBalanceOnBridge = new InternalButton();
	private InternalButton buttonShooterMove = new InternalButton();
	private InternalButton buttonShooterMove2 = new InternalButton();
	private InternalButton buttonShooterRun = new InternalButton();
	private InternalButton buttonShooterRun00 = new InternalButton();
	private InternalButton buttonShooterRun02 = new InternalButton();
	private InternalButton buttonShooterRun04 = new InternalButton();
	private InternalButton buttonShooterRun06 = new InternalButton();
	private InternalButton buttonShooterRun08 = new InternalButton();
	private InternalButton buttonShooterRun10 = new InternalButton();
	private InternalButton buttonShooterStop = new InternalButton();	
	
	public OI() {
		m_ds = new Driverstation();
		if(getDS().getDS().getDigitalIn(kDSDigitalInputDebugMode)) Debug.setMode(false);
		if(getDS().getDS().getTeamNumber() != kTeamNumber) Debug.println("[ERROR] Team number not "+ kTeamNumber +" on the Driver Station!");
		
		gamepad = new Gamepad(RobotMap.kJoystick1);
		joystick = new Gamepad(RobotMap.kJoystick2);
		joystick2 = new Gamepad(RobotMap.kJoystick3);
		
		// Arm buttons
		new JoystickButton(gamepad, kGamepadButtonLB).whenPressed(new ArmRun());
		new JoystickButton(joystick, kJoystickButtonThumbBottomRight).whenPressed(new ArmRun());
		new JoystickButton(joystick2, 1).whenPressed(new ArmRun());
		new JoystickButton(joystick2, 2).whenPressed(new ArmExtract(kArmSpeed, 3.5));
		new JoystickButton(joystick2, 3).whenPressed(new ArmRetract(kArmSpeed, 2.0));
		new JoystickButton(joystick2, 4).whenPressed(new ArmExtract(kArmSlowSpeed, 0.17));
		new JoystickButton(joystick2, 5).whileHeld(new ArmLatch());
		new JoystickButton(joystick2, 6).whileHeld(new ArmUnlatch());
		SmartDashboard.putData("ArmRun", buttonArmRun);
		buttonArmRun.whenPressed(new ArmRun());
		
		// Shooter angler buttons
		new JoystickButton(joystick, kJoystickButtonThumbTopLeft).whileHeld(new ShooterMove(kShooterUp));
		new JoystickButton(joystick, kJoystickButtonThumbBottomLeft).whileHeld(new ShooterMove(kShooterDown));
		new JoystickButton(joystick, kJoystickButtonTopLeft).whileHeld(new ShooterMove());
		new JoystickButton(gamepad, kGamepadButtonBack).whileHeld(new ShooterMove());
		SmartDashboard.putData("ShooterUp", buttonShooterUp);
		SmartDashboard.putData("ShooterDn", buttonShooterDn);
		SmartDashboard.putData("ShooterMove", buttonShooterMove);
		buttonShooterUp.whileHeld(new ShooterMove(kShooterUp));
		buttonShooterDn.whileHeld(new ShooterMove(kShooterDown));
		buttonShooterMove.whenPressed(new ShooterMove());
		
		// Feeder buttons
		new JoystickButton(gamepad, kGamepadButtonA).whenPressed(new ShooterFeed(kTimeFeedOneBall));
		new JoystickButton(joystick, kJoystickButtonTrigger).whenPressed(new ShooterFeed(kTimeFeedOneBall));
		SmartDashboard.putData("FeedBall", buttonFeedBall);
		buttonFeedBall.whenPressed(new ShooterFeed(kTimeFeedOneBall, kShooterForceFeed));
		
		// Shooter buttons
		new JoystickButton(gamepad, kGamepadButtonY).whenPressed(new ShooterRun(0.384));
		new JoystickButton(gamepad, kGamepadButtonX).whenPressed(new ShooterRun(0.305));
		new JoystickButton(gamepad, kGamepadButtonStart).whenPressed(new ShooterStop());
		new JoystickButton(joystick2, kJoystick2ButtonBottomLeft).whenPressed(new ShooterRun());
		new JoystickButton(joystick2, kJoystick2ButtonBottomRight).whenPressed(new ShooterStop());
		new JoystickButton(joystick, kJoystickButtonBottomLeft).whenPressed(new ShooterRun());
		new JoystickButton(joystick, kJoystickButtonBottomRight).whenPressed(new ShooterStop());
		SmartDashboard.putData("ShooterRun", buttonShooterRun);
		SmartDashboard.putData("ShooterRun02", buttonShooterRun02);
		SmartDashboard.putData("ShooterRun04", buttonShooterRun04);
		SmartDashboard.putData("ShooterRun06", buttonShooterRun06);
		SmartDashboard.putData("ShooterRun08", buttonShooterRun08);
		SmartDashboard.putData("ShooterRun10", buttonShooterRun10);
		SmartDashboard.putData("ShooterStop", buttonShooterStop);
		buttonShooterRun.whenPressed(new ShooterRun());
		buttonShooterRun02.whenPressed(new ShooterRun(0.2));
		buttonShooterRun04.whenPressed(new ShooterRun(0.4));
		buttonShooterRun06.whenPressed(new ShooterRun(0.6));
		buttonShooterRun08.whenPressed(new ShooterRun(0.8));
		buttonShooterRun10.whenPressed(new ShooterRun(1.0));
		buttonShooterStop.whenPressed(new ShooterStop());
		
		// Grabber buttons
		new JoystickButton(gamepad, kGamepadButtonRB).whileHeld(new GrabberReverse());
		new JoystickButton(gamepad, kGamepadButtonB).whenPressed(new GrabberRun());
		new JoystickButton(joystick, kJoystickButtonMidLeft).whenPressed(new GrabberRun());
		new JoystickButton(joystick, kJoystickButtonMidRight).whileHeld(new GrabberReverse());
		SmartDashboard.putData("GrabberRun", buttonGrabberRun);
		SmartDashboard.putData("GrabberStop", buttonGrabberStop);
		buttonGrabberRun.whenPressed(new GrabberRun());
		buttonGrabberStop.whileHeld(new GrabberReverse());
		
	}
	
	public static OI getInstance() {
		if (instance == null)
			instance = new OI();
		return instance;
	}

	public Gamepad getGamePad() {
		return gamepad;
	}

	public Gamepad getJoystick() {
		return joystick;
	}

	public Gamepad getJoystick2() {
		return joystick2;
	}

	public Gamepad getController(int controller) {
		switch (controller) {
			case 1:	return gamepad;
			case 2:	return joystick;
			case 3:	return joystick2;
			default: return gamepad;
		}
	}
	
	public Driverstation getDS() {
		return m_ds;
	}

}
