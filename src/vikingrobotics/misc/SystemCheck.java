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

public class SystemCheck extends CommandGroup implements Constants {
	
	public SystemCheck() {
		
		Debug.println("[SystemCheck] --- SYSTEM CHECK! ---");
		
		// Grabber
		Debug.print("[SystemCheck] Grabber Run");
		addSequential(new GrabberRun(1.0));
		Debug.println("\t\tDONE");
		Debug.print("[SystemCheck] Grabber Reverse");
		addSequential(new GrabberReverse(1.0));
		Debug.println("\tDONE");
		Debug.print("[SystemCheck] Grabber Stop");
		addSequential(new GrabberStop());
		Debug.println("\t\tDONE");
		
		// Shooter
		Debug.print("[SystemCheck] Shooter Run");
		addSequential(new ShooterRun(0.384), 3.0);
		Debug.println("\t\tDONE");
		Debug.print("[SystemCheck] Shooter Stop");
		addSequential(new ShooterStop());
		Debug.println("\t\tDONE");
		
		// Feeder
		Debug.print("[SystemCheck] Feeder Run");
		addSequential(new ShooterFeed(kTimeFeedOneBall, true));
		Debug.println("\t\tDONE");
		
		// Angler
		Debug.print("[SystemCheck] Shooter Up");
		addSequential(new ShooterMove(kShooterUp, 1.0));
		Debug.println("\t\tDONE");
		Debug.print("[SystemCheck] Shooter Down");
		addSequential(new ShooterMove(kShooterDown, 1.0));
		Debug.println("\t\tDONE");
		
		// Arm
		Debug.print("[SystemCheck] Arm Run");
		addSequential(new ArmRun());
		Debug.println("\t\t\tDONE");
		
	}

}
