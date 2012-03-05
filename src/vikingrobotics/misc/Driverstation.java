/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package vikingrobotics.misc;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.DriverStationLCD.Line;

/**
 * 
 * @author Neal
 */
public class Driverstation implements Constants {
	
	private DriverStation DS;
	private DriverStationIO IO;
	private DriverStationLCD LCD;
	private static final int kDefaultStartColumn = 1;
	private static final String kDefaultMessage = "                         ";
	
	public static final Line[] line = {
		DriverStationLCD.Line.kMain6,		// Mode
		DriverStationLCD.Line.kUser2,		// Arm status
		DriverStationLCD.Line.kUser3,
		DriverStationLCD.Line.kUser4,
		DriverStationLCD.Line.kUser5,
		DriverStationLCD.Line.kUser6
	};
	
	public Driverstation() {
		IO = new DriverStationIO();
		DS = DriverStation.getInstance();
		LCD = DriverStationLCD.getInstance();
		clearConsole();
		print(1, "Initializing...");
	}
	
	public DriverStation getDS() {
		return DS;
	}	
	
	/**
	 * Prints the specified string on the User Messages starting from the begining.
	 * @param lineNumber Line number. Could be from 1 to 6.
	 * @param Message The message to be printed.
	 */
	public void print(int lineNumber, String Message) {
		this.print(lineNumber, kDefaultStartColumn, Message);
	}

	/**
	 * Prints the specified string on the User Messages starting from the specified starting column.
	 * @param lineNumber Line number. Could be from 1 to 6.
	 * @param startColumn The starting column to use.
	 * @param Message The message to be printed.
	 */
	public void print(int lineNumber, int startColumn, String Message) {
		LCD.println(line[lineNumber-1], startColumn, kDefaultMessage);
		LCD.println(line[lineNumber-1], startColumn, Message);
		LCD.updateLCD();
	}

	/**
	 * Clear a specified line.
	 * @param lineNumber The line to be cleared.
	 */
	public void clearConsole(int lineNumber) {
		print(lineNumber, kDefaultStartColumn, kDefaultMessage);
	}
	
	/**
	 * Clear all lines.
	 */
	public void clearConsole() {
		for (int i = 1; i <= 6; i++) {
			clearConsole(i);
		}
	}
	
}
