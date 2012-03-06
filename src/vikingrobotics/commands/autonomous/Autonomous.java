/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package vikingrobotics.commands.autonomous;

import vikingrobotics.commands.CommandBase;
import vikingrobotics.misc.Constants;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Autonomous
 *  
 * @author Neal
 */
public class Autonomous extends CommandGroup implements Constants {
	
	private SendableChooser autonChooser;
	private Command AutonCommand;
	
	public Autonomous() {
		super("Autonomous");
		AutonCommand = getAutonCommdand();
		if (CommandBase.oi.getDS().getDS().getDigitalIn(kDSDigitalInputAutonomous)) {
			addSequential(new PrintCommand("[autonomous] Running " + AutonCommand));
			addSequential(AutonCommand);
		}
		else {
			addSequential(new PrintCommand("[autonomous] Digital Input 8 returned false; skipping Autonomous"));
		}
	}
	
	public Command getAutonCommdand() {
		autonChooser = new SendableChooser();
		autonChooser.addDefault("Auton0", new WaitCommand(1.0));
		autonChooser.addObject("Auton1", new Auton1());
		autonChooser.addObject("Auton2", new Auton2());
		autonChooser.addObject("Auton3", new Auton3());
		SmartDashboard.putData("Autonomous Setting", autonChooser);
		return (Command) autonChooser.getSelected();
	}

}
