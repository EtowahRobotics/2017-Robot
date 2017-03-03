/**
 * (c) 2017 Etowah Robotics
 */
package org.usfirst.frc.team6341.robot2017.auto.objective;

import org.usfirst.frc.team6341.robot2017.Robot;
import org.usfirst.frc.team6341.robot2017.auto.CollisionTracking;
import org.usfirst.frc.team6341.robot2017.auto.VisionTracking;

import edu.wpi.first.wpilibj.Timer;

/**
 * Drops off the 60 point gear in autonomous
 * @author Dan Mulloy
 */
public class GearObjective extends AutoObjective {
	private double speed;
	private double delay = 0.5;

	/**
	 * Constructs a gear objective
	 * @param speed Motor speed
	 */
	public GearObjective(double speed) {
		this.speed = speed;
	}

	@Override
	public void execute() {
		Robot.drivetrain.autoDrive(speed, 0);

		VisionTracking.listen("target_airship", (event) -> {
			if (event.area.length > 0) {
				for (double area : event.area) {
					if (area > 10_000) {
						speed /= 2;
						Robot.drivetrain.autoDrive(speed, 0);
					}
				}
			}
		});

		CollisionTracking.addListener(() -> finish());
	}

	@Override
	public void preFinish() {
		// Give Seth half a second
		Timer.delay(delay);
	}
}