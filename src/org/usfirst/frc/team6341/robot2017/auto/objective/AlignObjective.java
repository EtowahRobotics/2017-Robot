/**
 * (c) 2017 Etowah Robotics
 */
package org.usfirst.frc.team6341.robot2017.auto.objective;

import org.usfirst.frc.team6341.robot2017.Robot;
import org.usfirst.frc.team6341.robot2017.auto.CollisionTracking;
import org.usfirst.frc.team6341.robot2017.vision.VisionTracking;

import edu.wpi.first.wpilibj.DriverStation.Alliance;

/**
 * Aligns the robot with the high goal
 * @author Dan Mulloy
 */
public class AlignObjective extends DriveBasedObjective {
	private double speed;

	/**
	 * Constructs an align objective
	 * @param speed Motor speed
	 */
	public AlignObjective(double speed) {
		super(speed);
	}

	@Override
	public void start() {
		Robot.drivetrain.autoDrive(speed, 0);

		String table = getAlliance() == Alliance.Red ? VisionTracking.redBoilerTarget : VisionTracking.blueBoilerTarget;
		VisionTracking.listen(table, (event) -> {
			if (event.area.length > 0) {
				for (double area : event.area) {
					if (area > 1000) {
						// Aligning the robot is something we're going to have to do next week
						// For now we're just going to hope and pray we're aligned correctly
						finish();
						return;
					}
				}
			}
		});

		// Contingency plan
		CollisionTracking.addListener(() -> finish());
	}
}