/**
 * (c) 2017 Etowah Robotics
 */
package org.usfirst.frc.team6341.robot2017.tasks;

import java.util.TimerTask;

import org.usfirst.frc.team6341.robot2017.Robot;

/**
 * Stops and starts motor(s) gracefully without shredding gearboxes or spinning out
 * @author Dan Mulloy
 */
public class IterativeMotorTask extends TimerTask {
	private double speed;
	private double finalSpeed;
	private double delta;
	private long waitTime;

	private MotorInteractor interactor;

	private int sign;

	public IterativeMotorTask(double startSpeed, double finalSpeed, double delta, long waitTime, MotorInteractor interactor) {
		this.speed = limit(startSpeed);
		this.finalSpeed = limit(finalSpeed);
		this.waitTime = waitTime;
		this.interactor = interactor;
		this.delta = delta;
		this.sign = startSpeed > finalSpeed ? -1 : 1;

		start();
	}

	private static double limit(double value) {
		return Math.max(-1, Math.min(1, value));
	}

	private void start() {
		Robot.timer.schedule(this, 0, waitTime);
	}

	@Override
	public void run() {
		speed = limit(speed + (sign * delta));
		interactor.power(speed);

		if (speed >= finalSpeed || speed <= finalSpeed) {
			cancel();
		}
	}
}