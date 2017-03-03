/**
 * (c) 2017 Etowah Robotics
 */
package org.usfirst.frc.team6341.robot2017.auto;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team6341.robot2017.Robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Tracks collisions with the NavX, used in autonomous
 * @author Dan Mulloy
 */
public class CollisionTracking {
	public static interface CollisionListener {
		void onCollide();
	}

	private static final List<CollisionListener> listeners = new ArrayList<>();
	private static final double collisionThreshold = 0.5F;

	private static double lastAccelX;
	private static double lastAccelY;
	
	public static void addListener(CollisionListener listener) {
		listeners.add(listener);
	}

	private static void callListeners() {
		for (CollisionListener listener : listeners) {
			listener.onCollide();
		}
	}

	public static void tick() {
		double accelX = Robot.navX.getAccelX();
		double accelY = Robot.navX.getAccelY();
		double jerkX = accelX - lastAccelX;
		double jerkY = accelY - lastAccelY;
		lastAccelX = accelX;
		lastAccelY = accelY;

		if (Math.abs(jerkX) > collisionThreshold ||
				Math.abs(jerkY) > collisionThreshold) {
			callListeners();
			SmartDashboard.putBoolean("collision", true);
		} else {
			SmartDashboard.putBoolean("collision", false);
		}
	}
}