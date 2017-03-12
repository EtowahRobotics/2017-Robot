/**
 * (c) 2017 Etowah Robotics
 */
package org.usfirst.frc.team6341.robot2017.auto.objective;

import org.usfirst.frc.team6341.robot2017.Robot;

/**
 * Drives straight for some amount of seconds
 * @author Dan Mulloy
 */
public class DriveObjective extends DriveBasedObjective {

	/**
	 * Constructs a drive objective
	 * @param speed Motor speed from -1 to 1
	 * @param time Time in seconds
	 */
	public DriveObjective(double speed, double curve, double time) {
		super(speed, curve, time);
	}

	@Override
	public void start() {
		setTimeout();
		Robot.drivetrain.autoDrive(speed, curve);
	}
}