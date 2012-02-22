package vikingrobotics.commands.arm;

import vikingrobotics.commands.delay;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class ArmRun extends CommandGroup {
	
	public ArmRun() {
		super("ArmRun");
		/*
		 * Extract the arm all the way until sensor
		 */
		addSequential(new ArmExtract(1.0));
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
		addSequential(new ArmRetract(1.0));
		/*
		 * Extract the arm for 0.2 seconds at slow speed
		 */
		addSequential(new ArmExtract(0.2, true));
	}
	
}
