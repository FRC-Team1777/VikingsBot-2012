/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package vikingrobotics.misc;

/**
 * Three Laws of Robotics.
 * 
 * @author Isaac Asimov
 */
public interface RobotLaws {
	
	/*
	 * 1. A robot may not injure a human being or, through inaction, allow a human being to come to harm.
	 */
	public void DoNotHarmHumans();
	
	/*
	 * 2. A robot must obey orders given it by human beings except where such orders would conflict with the First Law.
	 */
	public void ObeyOrders();
	
	/*
	 * 3. A robot must protect its own existence as long as such protection does not conflict with the First or Second Law.
	 */
	public void ProtectYourself();
	
}
