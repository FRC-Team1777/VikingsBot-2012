/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package vikingrobotics.commands.arm;

import vikingrobotics.commands.delay;
import vikingrobotics.misc.Constants;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ArmRun extends CommandGroup implements Constants {
	
	public ArmRun() {
		super("ArmRun");
		/*
		 * Extract the arm all the way until sensor
		 */
		addSequential(new ArmExtract(kArmSpeed, 1.0));
		/*
		 * Latch the arm
		 */
		//addSequential(new ArmLatch(timeout));
		/*
		 * Wait for 5 seconds; give time to get on the bridge
		 */
		addSequential(new delay(5.0));
		/*
		 * Unlatch the arm
		 */
		//addSequential(new ArmUnlatch(timeout));
		/*
		 * Retract back the arm all the way until sensor
		 */
		addSequential(new ArmRetract(kArmSpeed, 1.0));
		/*
		 * Extract the arm for 0.2 seconds at slow speed
		 */
		addSequential(new ArmExtract(kArmSlowSpeed, 0.2));
	}
	
}
