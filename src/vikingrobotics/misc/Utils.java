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
	 * @param decimals The number of decimal places.
	 * @return The input number rounded to the number of decimal places specified.
	 */
	public static double roundDecimals(double x, int decimals) {
		if (decimals > 9) return (Math.ceil(x * decimals) / decimals);
		double z = 1;
		for(int i = 1; i <= decimals; i++) z *= 10;
		return (Math.ceil(x * z) / z);
	}
	
	/**
	 * Scale battery voltage from 0.0 to 100.00 that's proportional to 8.00 - 13.00
	 * @param x Battery voltage between 8.00 - 13.00
	 * @return Scaled battery voltage from 0.00 to 100.00
	 */
	public static double scaleBatteryVoltage(double x) {
		return (20 * x) - 160;
	}
	
	/**
	 * Scale a number between two specified numbers.
	 * eg. Scale 0.6 between 0.3 and 0.7
	 * @param x The number to be scaled
	 * @param min Minimum value
	 * @param max Maximum value
	 * @return Scaled value between min and max
	 */
	public static double scaleBetween(double x, double min, double max) {
		return min + ( x * (max - min) );
	}

	/**
	 * Scale the value of the throttle from 0.0 - 1.0 instead of -1.0 - 1.0
	 * @param x The throttle value
	 * @return scaled throttle value
	 */
	public static double scaleThrottle(double x) {
		return (x + 1) / 2;
	}

	/**
	 * Overwrite joystick values in a way that 0.0-1.0 is proportional to 0.2-1.0
	 * @param x The joystick value that needs to be rounded up.
	 */
	private static double scale(double x) {
		if (Math.abs(x) < kJoystickThreshold) return 0;
		if (x > 0) return (x - kJoystickThreshold) / (1 - kJoystickThreshold);
		if (x < 0) return (x + kJoystickThreshold) / (1 - kJoystickThreshold);
		return 0;
	}

}
