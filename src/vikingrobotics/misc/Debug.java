/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package vikingrobotics.misc;

/**
 * Print anything to the console
 * @author Neal
 */
public class Debug {
	
	private static boolean kDebugMode = true;

    /**
     * If in debug mode, prints specified string without a newline
     * @param s
     */
    public static void print(String s) {
        if (kDebugMode) {
            System.out.print(s);
        }
    }

    /**
     * If in debug mode, prints specified string with a newline
     * @param s
     */
    public static void println(String s) {
        if (kDebugMode) {
            System.out.println(s);
        }
    }

    /**
     * If in debug mode, prints specified string with a newline
     * @param s
     */
    public static void err(String s) {
        if (kDebugMode) {
            System.err.println(s);
        }
    }

    /**
     * Returns true if in debug mode, false otherwise.
     * @return kDebugMode
     */
    public static boolean getMode() {
        return kDebugMode;
    }

    /**
     * Set the value for kDebugMode
     */
    public static void setMode(boolean mode) {
        kDebugMode = mode;
    }

}
