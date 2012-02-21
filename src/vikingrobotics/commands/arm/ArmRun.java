package vikingrobotics.commands.arm;

import vikingrobotics.misc.Debug;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ArmRun extends CommandGroup {
	
	public ArmRun() {
		super("ArmRun");
		addSequential(new ArmExtract(2.0));
		//addSequential(new ArmLatch());
		addSequential(new ArmCheckForRetraction(5.0));
		//addSequential(new ArmUnlatch());
		addSequential(new ArmRetract(2.0));
		addSequential(new ArmSet(0.2));
		addSequential(new ArmCancel());
	}
	
}
