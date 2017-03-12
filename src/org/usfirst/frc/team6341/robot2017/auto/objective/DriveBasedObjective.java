/**
 * (c) 2017 Etowah Robotics
 */
package org.usfirst.frc.team6341.robot2017.auto.objective;

import org.usfirst.frc.team6341.robot2017.Robot;

/**
 * Common code for drive-based objectives
 * @author Dan Mulloy
 */
public abstract class DriveBasedObjective extends AutoObjective {
	protected double speed;
	protected double curve;
	protected double timeout;

	protected DriveBasedObjective(double speed, double curve, double timeout) {
		this.speed = speed;
		this.curve = curve;
		this.timeout = timeout;
	}

	protected DriveBasedObjective(double speed, double timeout) {
		this(speed, 0, timeout);
	}

	public abstract void start();

	protected void setTimeout() {
		super.timeout(timeout);
	}

	@Override
	public void periodic() {
		Robot.drivetrain.autoDrive(speed, curve);
	}
}