/**
 * (c) 2017 Etowah Robotics
 */
package org.usfirst.frc.team6341.robot2017.tasks;

import java.util.TimerTask;

import org.usfirst.frc.team6341.robot2017.Timers;

/**
 * Stops and starts motor(s) gracefully without shredding gearboxes or spinning out
 * @author Dan Mulloy
 */
public class IterativeMotorTask implements Runnable {
	private double speed;
	private double finalSpeed;
	private double delta;

	private MotorInteractor interactor;
	private TimerTask task;

	private int sign;

	public IterativeMotorTask(double startSpeed, double finalSpeed, double delta, double waitTime, MotorInteractor interactor) {
		this.speed = limit(startSpeed);
		this.finalSpeed = limit(finalSpeed);
		this.interactor = interactor;
		this.delta = delta;
		this.sign = startSpeed > finalSpeed ? -1 : 1;
		this.task = Timers.schedule(this, waitTime);
	}

	private static double limit(double value) {
		return Math.max(-1, Math.min(1, value));
	}

	@Override
	public void run() {
		speed = limit(speed + (sign * delta));
		interactor.power(speed);

		if (speed >= finalSpeed || speed <= finalSpeed) {
			task.cancel();
		}
	}
}