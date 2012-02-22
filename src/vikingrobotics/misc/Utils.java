/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package vikingrobotics.misc;

/**
 * Random Utils
 * @author Neal
 */
public class Utils implements Constants {

	/**
	 * Rounds a double to specified number of decimal places.
	 * @param x The input number that needs to be rounded.
	 * @param decimals The number of decimal places. (eg. 100 for 2 decimal places)
	 * @return The input number rounded to the number of decimal places specified.
	 */
	public double roundDecimal(double x, int decimals) {
		return (Math.ceil(x * decimals) / decimals);
	}

	/**
	 * Scale the value of the throttle from 0.0 - 1.0 instead of -1.0 - 1.0
	 * @param x The throttle value
	 * @return scaled throttle value
	 */
	public double scaleThrottle(double x) {
		return (x + 1) / 2;
	}

	/**
	 * Overwrite joystick values in a way that 0.0-1.0 is proportional to 0.2-1.0
	 * @param x The joystick value that needs to be rounded up.
	 */
	private double scale(double x) {
		
		if (Math.abs(x) < kJoystickThreshold) return 0;
		if (x > 0) return (x - kJoystickThreshold) / (1 - kJoystickThreshold);
		if (x < 0) return (x + kJoystickThreshold) / (1 - kJoystickThreshold);
		return 0;
	}

}
