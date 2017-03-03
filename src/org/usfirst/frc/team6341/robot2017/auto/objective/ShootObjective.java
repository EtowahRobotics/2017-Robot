/**
 * (c) 2017 Etowah Robotics
 */
package org.usfirst.frc.team6341.robot2017.auto.objective;

import org.usfirst.frc.team6341.robot2017.Robot;

/**
 * Shoots 40 balls in autonomous
 * @author Dan Mulloy
 */
public class ShootObjective extends AutoObjective {

	@Override
	public void execute() {
		Robot.turret.setPower(0.9);
		Robot.turret.spinUp();

		// TODO Stop executing once we've shot 40 or autonomous ends
	}
}