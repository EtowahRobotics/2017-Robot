/**
 * (c) 2017 Etowah Robotics
 */
package org.usfirst.frc.team6341.robot2017.auto.objective;

import org.usfirst.frc.team6341.robot2017.Robot;

import edu.wpi.first.wpilibj.Timer;

/**
 * Drives straight for some amount of seconds
 * @author Dan Mulloy
 */
public class DriveObjective extends AutoObjective {
	private double speed;
	private double time;

	/**
	 * Constructs a drive objective
	 * @param speed Motor speed from -1 to 1
	 * @param time Time in seconds
	 */
	public DriveObjective(double speed, double time) {
		this.speed = speed;
		this.time = time;
	}

	@Override
	public void execute() {
		Robot.drivetrain.autoDrive(speed, 0);
		Timer.delay(time);
		finish();
	}
}