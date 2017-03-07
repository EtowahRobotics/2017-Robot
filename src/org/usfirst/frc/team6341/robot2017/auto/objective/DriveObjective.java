/**
 * (c) 2017 Etowah Robotics
 */
package org.usfirst.frc.team6341.robot2017.auto.objective;

import java.util.TimerTask;

import org.usfirst.frc.team6341.robot2017.Robot;

/**
 * Drives straight for some amount of seconds
 * @author Dan Mulloy
 */
public class DriveObjective extends DriveBasedObjective {
	private double speed;
	private double time;

	/**
	 * Constructs a drive objective
	 * @param speed Motor speed from -1 to 1
	 * @param time Time in seconds
	 */
	public DriveObjective(double speed, double time) {
		super(speed);
		this.time = time;
	}

	@Override
	public void start() {
		Robot.drivetrain.autoDrive(speed, 0);
		Robot.timer.schedule(new TimerTask() {
			@Override
			public void run() {
				finish();
			}
		}, ((long) time * 1000));
	}
}