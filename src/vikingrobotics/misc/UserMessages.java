/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package vikingrobotics.misc;

import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.DriverStationLCD.Line;

/**
 * Prints the specified string on the User Messages section on the Driver Station.
 *
 * @author Neal
 */
public class UserMessages {
	
	private DriverStationLCD uM;
	
	public static final Line[] line = {
		DriverStationLCD.Line.kMain6,
		DriverStationLCD.Line.kUser2,
		DriverStationLCD.Line.kUser3,
		DriverStationLCD.Line.kUser4,
		DriverStationLCD.Line.kUser5,
		DriverStationLCD.Line.kUser6
	};
	

	/**
	 * UserMessages constructor
	 * 
	 */
	public UserMessages() {
		uM = DriverStationLCD.getInstance();
		init();
	}

	/**
	 * Initialize User Messages. Clear all lines and update 1st line.
	 * 
	 */
	private void init() {
		clearAll();
		write(1, 1, "Initializing...");
	}

	/**
	 * Prints the specified string on the User Messages starting from the begining.
	 * @param lineNumber Line number. Could be from 1 to 6.
	 * @param Message The message to be printed.
	 */
	public void write(int lineNumber, String Message) {
		write(lineNumber, 1, Message);
	}

	/**
	 * Prints the specified string on the User Messages starting from the specified starting column.
	 * @param lineNumber Line number. Could be from 1 to 6.
	 * @param startColumn The starting column to use.
	 * @param Message The message to be printed.
	 */
	public void write(int lineNumber, int startColumn, String Message) {
		uM.println(line[lineNumber-1], startColumn, "                         ");
		uM.println(line[lineNumber-1], startColumn, Message);
		uM.updateLCD();
	}

	/**
	 * Clear a specified line.
	 * @param lineNumber The line to be cleared.
	 */
	public void clear(int lineNumber) {
		write(lineNumber, 1, "");
	}
	
	/**
	 * Clear all lines.
	 * 
	 */
	public void clearAll() {
		for (int i = 1; i <= 6; i++) {
			write(i, 1, "");
		}
	}

}
