package vikingrobotics.commands.autonomous;

import vikingrobotics.commands.arm.ArmExtract;
import vikingrobotics.commands.drivetrain.DriveStraight;
import vikingrobotics.commands.grabber.GrabberRun;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class Auton1 extends CommandGroup {
	
	public Auton1() {
		super("Auton1");
		addSequential(new AutonShoot());
		addSequential(new DriveStraight(0.0, 0.0));
		addParallel(new GrabberRun());
		addSequential(new ArmExtract());
	}

}
