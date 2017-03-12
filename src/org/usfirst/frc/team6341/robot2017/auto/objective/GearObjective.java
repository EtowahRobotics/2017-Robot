/**
 * (c) 2017 Etowah Robotics
 */
package org.usfirst.frc.team6341.robot2017.auto.objective;

import org.usfirst.frc.team6341.robot2017.Robot;
import org.usfirst.frc.team6341.robot2017.auto.CollisionTracking;

import edu.wpi.first.wpilibj.Timer;

/**
 * Drops off the 60 point gear in autonomous
 * @author Dan Mulloy
 */
public class GearObjective extends DriveBasedObjective {
	private double speed;
	private double delay = 2.0;

	/**
	 * Constructs a gear objective
	 * @param speed Motor speed
	 * @param curve Drive curve
	 * @param timeout Objective timeout
	 */
	public GearObjective(double speed, double curve, double timeout) {
		super(speed, curve, timeout);
	}

	@Override
	public void start() {
		setTimeout();
		Robot.drivetrain.autoDrive(speed, curve);

		// TODO I'd like to use vision tracking with the gears

		/* VisionTracking.listen("target_airship", (event) -> {
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
		}); */

		CollisionTracking.addListener(() -> finish());
	}

	@Override
	public void preFinish() {
		// Wait two seconds
		Timer.delay(delay);
	}
}