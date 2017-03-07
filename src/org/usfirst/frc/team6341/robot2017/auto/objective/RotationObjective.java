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
	private double speed;

	private double delta;
	private double end;

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
	public void start() {
		if (delta == 0) {
			finish();
			return;
		}

		double start = Robot.navX.getAngle();
		this.end = start + delta;
		periodic();
	}

	@Override
	public void periodic() {
		if (delta > 0) {
			if (end < Robot.navX.getAngle()) {
				Robot.drivetrain.power(-speed, speed);
			} else {
				finish();
			}
		} else {
			if (end > Robot.navX.getAngle()) {
				Robot.drivetrain.power(speed, -speed);
			} else {
				finish();
			}
		}
	}
}