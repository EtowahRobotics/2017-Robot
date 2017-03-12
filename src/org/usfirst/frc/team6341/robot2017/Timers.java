/**
 * (c) 2017 Etowah Robotics
 */
package org.usfirst.frc.team6341.robot2017;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Contains timers for autonomous and teleop
 * @author Dan Mulloy
 */
public class Timers {
	private static Timer auto;
	private static Timer teleop;

	public static TimerTask scheduleAuto(Runnable run, double timeout) {
		if (auto == null)
			auto = new Timer();

		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				run.run();
			}
		};

		auto.schedule(task, (long) (timeout * 1000));
		return task;
	}

	public static void cancelAuto() {
		auto.cancel();
	}

	public static TimerTask scheduleTeleop(Runnable run, double timeout) {
		if (teleop == null)
			teleop = new Timer();

		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				run.run();
			}
		};

		teleop.schedule(task, (long) (timeout * 1000));
		return task;
	}

	public static void cancelTeleop() {
		teleop.cancel();
	}

	public static TimerTask schedule(Runnable run, double timeout) {
		if (Robot.getInstance().isAutonomous()) {
			return scheduleAuto(run, timeout);
		} else {
			return scheduleTeleop(run, timeout);
		}
	}

	public static void cancelAll() {
		auto.cancel();
		teleop.cancel();
	}
}