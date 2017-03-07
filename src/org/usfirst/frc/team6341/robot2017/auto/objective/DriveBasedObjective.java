package org.usfirst.frc.team6341.robot2017.auto.objective;

import org.usfirst.frc.team6341.robot2017.Robot;

public abstract class DriveBasedObjective extends AutoObjective {
	protected double speed;

	protected DriveBasedObjective(double speed) {
		this.speed = speed;
	}

	public abstract void start();

	@Override
	public void periodic() {
		Robot.drivetrain.autoDrive(speed, 0);
	}
}
