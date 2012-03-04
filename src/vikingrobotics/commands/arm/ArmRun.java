/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package vikingrobotics.commands.arm;

import vikingrobotics.misc.Constants;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ArmRun extends CommandGroup implements Constants {
	
	// Timeouts are there incase sensors don't work.
	// We don't want to break the motors :P
	
	private double ARM_EXTRACT_TIMEOUT = 3.5;
	private double ARM_LATCH_TIMEOUT = 0.5;
	private double DELAY_TIME = 7.0;
	private double ARM_UNLATCH_TIMEOUT = 0.4;
	private double ARM_RETRACT_TIMEOUT = 2.0;
	private double ARM_EXTRACT2_TIMEOUT = 0.17;
	
	public ArmRun() {
		super("ArmRun");
		/*
		 * Extract the arm all the way until it hits the sensor
		 */
		addSequential(new ArmExtract(kArmSpeed, ARM_EXTRACT_TIMEOUT));
		addSequential(new WaitCommand(0.5));
		/*
		 * Latch the arm until it hits the sensor, with a timeout of 0.5 seconds
		 */
		addSequential(new ArmLatch(ARM_LATCH_TIMEOUT));
		/*
		 * Wait for 7 seconds; give time to get on the bridge
		 */
		addSequential(new WaitCommand(DELAY_TIME));
		/*
		 * Unlatch the arm for 0.4 seconds
		 */
		addSequential(new ArmUnlatch(ARM_UNLATCH_TIMEOUT));
		/*
		 * Retract back the arm all the way until it hits the sensor
		 */
		addSequential(new ArmRetract(kArmSpeed, ARM_RETRACT_TIMEOUT));
		/*
		 * Extract the arm for 0.17 seconds at slow speed
		 */
		addSequential(new ArmExtract(kArmSlowSpeed, ARM_EXTRACT2_TIMEOUT));
	}
	
}
