/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package vikingrobotics.commands.autonomous;

import vikingrobotics.commands.CommandBase;
import vikingrobotics.misc.Constants;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;

/**
 * Autonomous
 *  
 * @author Neal
 */
public class Autonomous extends CommandGroup implements Constants {
	
	public Autonomous() {
		super("Autonomous");
		if (CommandBase.oi.getDS().getDS().getDigitalIn(kDSDigitalInputAutonomous)) {
			addSequential(new PrintCommand("[autonomous] Running auton1"));
			addSequential(new Auton1());
		}
		else {
			addSequential(new PrintCommand("[autonomous] Digital Input 8 returned false; skipping Autonomous"));
		}
	}

}
