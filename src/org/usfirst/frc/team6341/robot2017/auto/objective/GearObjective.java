/**
 * (c) 2017 Etowah Robotics
 */
package org.usfirst.frc.team6341.robot2017.auto.objective;

import org.usfirst.frc.team6341.robot2017.Robot;
import org.usfirst.frc.team6341.robot2017.auto.CollisionTracking;
import org.usfirst.frc.team6341.robot2017.vision.VisionTracking;

import edu.wpi.first.wpilibj.Timer;

/**
 * Drops off the 60 point gear in autonomous
 * @author Dan Mulloy
 */
public class GearObjective extends DriveBasedObjective {
	private double speed;
	private double delay = 1.0;

	private boolean slowed = false;

	/**
	 * Constructs a gear objective
	 * @param speed Motor speed
	 */
	public GearObjective(double speed) {
		super(speed);
	}

	@Override
	public void start() {
		Robot.drivetrain.autoDrive(speed, 0);

		VisionTracking.listen("target_airship", (event) -> {
			if (event.area.length > 0 && !slowed) {
				for (double area : event.area) {
					if (area > 10_000) {
						speed /= 2;
						Robot.drivetrain.autoDrive(speed, 0);
						slowed = true;
						break;
					}
				}
			}
		});

		CollisionTracking.addListener(() -> finish());
	}

	@Override
	public void preFinish() {
		// Give Seth a second
		Timer.delay(delay);
	}
}