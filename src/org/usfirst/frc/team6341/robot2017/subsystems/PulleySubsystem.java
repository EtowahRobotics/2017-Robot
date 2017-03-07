/**
 * (c) 2017 Etowah Robotics
 */
package org.usfirst.frc.team6341.robot2017.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Pulley system for delivering balls to the turret
 * @author Dan Mulloy
 */
public class PulleySubsystem extends EagleSubsystem {
	private static final double POWER = 0.75;

	private Spark pulley1;
	// private Spark pulley2;
	
	private boolean running;

	public PulleySubsystem() {
		try {
			// pulley1 = new Spark(RobotMap.pulley1);
			// pulley2 = new Spark(RobotMap.pulley2);
			
			// pulley1.set(0);
			// pulley2.set(0);
		} catch (Throwable ex) {
			// They must not be connected. Oh well.
		}
	}

	public void start() {
		if (running) return;

		pulley1.set(POWER);
		// pulley2.set(POWER);
		running = true;
		updateDashboard();
	}

	public void stop() {
		if (!running) return;

		pulley1.set(0);
		// pulley2.set(0);
		running = false;
		updateDashboard();
	}

	@Override
	public void updateDashboard() {
		SmartDashboard.putBoolean("runningPulleys", running);
	}
}