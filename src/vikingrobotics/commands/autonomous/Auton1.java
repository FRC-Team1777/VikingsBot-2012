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
	
	private static double forwards = -1;
	private static double backwards = 1;
	private double SHOOTER_SPEED = 0.305;
	private double DRIVE_SPEED = 0.7;
	private double DRIVE_TIMEOUT = 3.0;
	private double SHOOTER_WAIT_TIMEOUT = 5.0;
	private double GRABBER_TIMEOUT = 2.5;
	private double WAIT_ONE_SECOND = 1.0;
	private double DRIVE_BACK_TIMEOUT = 3.0;
	private double DRIVE_BACK_SPEED = 0.9;
	
	public Auton1() {
		super("Auton1");
		/*
		 * Run shooter at 0.305 speed
		 */
		addParallel(new ShooterRun(0.305));
		/*
		 * Drive forwards to the fender at 0.7 speed for 3 seconds
		 */
		addSequential(new DriveStraight(-0.7, 3.0));
		/*
		 * Run shooter again, in case it didn't before :/
		 */
		addParallel(new ShooterRun(0.305));
		/*
		 * Wait for 5 seconds until the shooter gets ready
		 */
		addSequential(new delay(5.0));
		/*
		 * Feed one ball
		 */
		addSequential(new ShooterFeed(kTimeFeedOneBall));
		/*
		 * Run grabber for 3.5 seconds to get the other ball to the shooter
		 */
		addSequential(new GrabberRun(), 2.5);
		/*
		 * Feed another ball
		 */
		addSequential(new ShooterFeed(kTimeFeedOneBall));
		/*
		 * Wait one second
		 */
		addSequential(new delay(1.0));
		/*
		 * Drive back as far as it can (time up)
		 */
		addSequential(new DriveStraight(0.8, 2.0));
		/*
		 * Maybe helps stop the previous command?
		 */
		addSequential(new delay(1.0));
	}

}
