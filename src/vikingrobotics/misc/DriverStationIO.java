/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package vikingrobotics.misc;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationEnhancedIO;

/**
 * 
 * @author Neal
 */
public class DriverStationIO implements Constants {
	
	DriverStationEnhancedIO enhancedIO;
	
	public DriverStationIO() {
		enhancedIO = DriverStation.getInstance().getEnhancedIO();
	}
	
	private boolean getDigital(int channel) {
		try {
			return enhancedIO.getDigital(channel);
		}
		catch (Exception e) {
			Debug.println("[ERROR] DS IO error 1 - " + e);
			return false;
		}
	}

}
