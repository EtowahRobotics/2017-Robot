/**
 * (c) 2017 Etowah Robotics
 */
package org.usfirst.frc.team6341.robot2017.subsystems;

import org.usfirst.frc.team6341.robot2017.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Subsystem containing the climber
 * @author Dan Mulloy
 */
public class ClimberSubsystem extends EagleSubsystem {
	private CANTalon motor;
	private boolean running = false;

	public ClimberSubsystem() {
		this.motor = new CANTalon(RobotMap.climber);
		motor.set(0);
	}

	public void power(double power) {
		power = -power;
		motor.set(power);
		running = power > 0;
		updateDashboard();
	}

	@Override
	public void updateDashboard() {
		SmartDashboard.putBoolean("climbing", running);
	}
}