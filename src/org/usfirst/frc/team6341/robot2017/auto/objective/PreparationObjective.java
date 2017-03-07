/**
 * (c) 2017 Etowah Robotics
 */
package org.usfirst.frc.team6341.robot2017.auto.objective;

import org.usfirst.frc.team6341.robot2017.Robot;

/**
 * Prepares the robot for autonomous
 * @author Dan Mulloy
 */
public class PreparationObjective extends AutoObjective {

	@Override
	public void start() {
		Robot.navX.reset();
		finish();
	}

	@Override
	public void periodic() { }
}