/**
 * (c) 2017 Etowah Robotics
 */
package org.usfirst.frc.team6341.robot2017.auto.objective;

import org.usfirst.frc.team6341.robot2017.Robot;
import org.usfirst.frc.team6341.robot2017.auto.CollisionTracking;

import edu.wpi.first.wpilibj.Timer;

/**
 * Hits a hopper in autonomous
 * @author Dan Mulloy
 */
public class HopperObjective extends AutoObjective {
	private double speed;
	private double delay = 2;

	/**
	 * Constructs a hopper objective
	 * @param speed Motor speed
	 */
	public HopperObjective(double speed) {
		this.speed = speed;
	}

	@Override
	public void start() {
		Robot.drivetrain.autoDrive(speed, 0);
		CollisionTracking.addListener(() -> finish());
	}

	@Override
	public void periodic() {
		Robot.drivetrain.autoDrive(speed, 0);
	}

	@Override
	public void preFinish() {
		// Hoppers take about 2 seconds to fully unload
		Timer.delay(delay);
	}
}