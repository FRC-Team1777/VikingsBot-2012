/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package vikingrobotics.misc;

import vikingrobotics.commands.arm.ArmRun;
import vikingrobotics.commands.grabber.GrabberReverse;
import vikingrobotics.commands.grabber.GrabberRun;
import vikingrobotics.commands.grabber.GrabberStop;
import vikingrobotics.commands.shooter.ShooterFeed;
import vikingrobotics.commands.shooter.ShooterMove;
import vikingrobotics.commands.shooter.ShooterRun;
import vikingrobotics.commands.shooter.ShooterStop;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class SystemCheck extends CommandGroup implements Constants {
	
	public SystemCheck() {
		
		addSequential(new PrintCommand("[SystemCheck] --- SYSTEM CHECK! ---"));
		
		// Grabber
		addSequential(new PrintCommand("[SystemCheck] Grabber Run"));
		addSequential(new GrabberRun(1.0));
		addSequential(new PrintCommand("[SystemCheck] Grabber Reverse"));
		addSequential(new GrabberReverse(1.0));
		addSequential(new PrintCommand("[SystemCheck] Grabber Stop"));
		addSequential(new GrabberStop());
		addSequential(new WaitCommand(1.0));
		
		// Shooter
		addSequential(new PrintCommand("[SystemCheck] Shooter Run"));
		addSequential(new ShooterRun(0.384), 3.0);
		addSequential(new PrintCommand("[SystemCheck] Shooter Stop"));
		addSequential(new ShooterStop());
		addSequential(new WaitCommand(1.0));
		
		// Feeder
		addSequential(new PrintCommand("[SystemCheck] Feeder Run"));
		addSequential(new ShooterFeed(kTimeFeedOneBall, kShooterForceFeed));
		addSequential(new WaitCommand(1.0));
		
		// Angler
		addSequential(new PrintCommand("[SystemCheck] Shooter Up"));
		addSequential(new ShooterMove(kShooterUp, 1.0));
		addSequential(new PrintCommand("[SystemCheck] Shooter Down"));
		addSequential(new ShooterMove(kShooterDown, 1.0));
		addSequential(new WaitCommand(1.0));
		
		// Arm
		addSequential(new PrintCommand("[SystemCheck] Arm Run"));
		addSequential(new ArmRun());
		addSequential(new WaitCommand(1.0));
		
	}

}
