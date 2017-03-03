/**
 * (c) 2017 Etowah Robotics
 */
package org.usfirst.frc.team6341.robot2017.auto.objective;

import org.usfirst.frc.team6341.robot2017.Robot;

/**
 * Rotates the robot a certain number of degrees
 * @author Dan Mulloy
 */
public class RotationObjective extends AutoObjective {
	private double delta;
	private double speed;

	/**
	 * Constructs a rotation objective
	 * @param delta Change in angle
	 * @param speed Motor speed
	 */
	public RotationObjective(double delta, double speed) {
		this.delta = delta;
		this.speed = speed;
	}

	@Override
	public void execute() {
		double start = Robot.navX.getAngle();
		double end = start + delta;
		if (delta > 0) {
			while (end < Robot.navX.getAngle()) {
				Robot.drivetrain.power(-speed, speed);
			}
		} else {
			while (end > Robot.navX.getAngle()) {
				Robot.drivetrain.power(speed, -speed);
			}
		}
	}
}