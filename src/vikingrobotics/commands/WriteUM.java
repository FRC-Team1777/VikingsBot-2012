/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package vikingrobotics.commands;

/**
 * 
 * @author Neal
 */
public class WriteUM extends CommandBase {
	
	private int line;
    private String message;
    
    public WriteUM(int line, String message) {
    	super("WriteUM");
    	this.line = line;
    	this.message = message;
    }
    
	protected void initialize() {
		oi.getDS().print(this.line, this.message);
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
	}

	protected void interrupted() {
	}

}
