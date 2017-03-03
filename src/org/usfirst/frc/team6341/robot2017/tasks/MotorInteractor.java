/**
 * (c) 2017 Etowah Robotics
 */
package org.usfirst.frc.team6341.robot2017.tasks;

/**
 * Interacts with motors. Pretty simple if you ask me.
 * @author Dan Mulloy
 */
@FunctionalInterface
public interface MotorInteractor {
	void power(double speed);
}