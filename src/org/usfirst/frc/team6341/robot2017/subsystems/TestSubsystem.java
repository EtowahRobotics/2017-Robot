/**
 * (c) 2017 Etowah Robotics
 */
package org.usfirst.frc.team6341.robot2017.subsystems;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Testing subsystem
 * @author Dan Mulloy
 */
public class TestSubsystem extends EagleSubsystem {
	private CANTalon talon;

	public TestSubsystem() {
		try {
			talon = new CANTalon(0);
			talon.set(0.0D);
		} catch (Throwable ex) {
			// It must not be connected or whatever
			// Don't worry about it
		}
	}

	public void power(double power) {
		talon.set(power);
		updateDashboard();
	}

	public boolean isRunning() {
		return talon != null;
	}

	@Override
	public void updateDashboard() {
		SmartDashboard.putBoolean("testing", talon != null && talon.getSpeed() > 0);
		try {
			SmartDashboard.putNumber("rpms", talon != null ? talon.getSpeed() : -1);
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}
}