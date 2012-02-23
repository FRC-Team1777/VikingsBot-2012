/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package vikingrobotics.commands.autonomous;

import vikingrobotics.commands.delay;
import vikingrobotics.commands.arm.ArmExtract;
import vikingrobotics.commands.drivetrain.DriveStraight;
import vikingrobotics.commands.grabber.GrabberRun;
import vikingrobotics.commands.grabber.GrabberStop;
import vikingrobotics.commands.shooter.ShooterFeed;
import vikingrobotics.commands.shooter.ShooterMove;
import vikingrobotics.commands.shooter.ShooterRun;
import vikingrobotics.misc.Constants;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class Auton1 extends CommandGroup implements Constants {
	
	public Auton1() {
		super("Auton1");
		addParallel(new GrabberRun());
		addParallel(new ShooterRun(0.384));
		addSequential(new ShooterMove(kShooterUp, 2.0));
		addSequential(new delay(1.0));
		addSequential(new ShooterFeed(kTimeFeedOneBall));
		addSequential(new delay(1.0));
		addSequential(new ShooterFeed(kTimeFeedOneBall));
		addParallel(new GrabberStop());
		addSequential(new DriveStraight(-0.5, 3.0));
	}

}
