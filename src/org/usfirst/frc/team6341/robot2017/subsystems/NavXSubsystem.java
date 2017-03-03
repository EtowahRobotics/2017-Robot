/**
 * (c) 2017 Etowah Robotics
 */
package org.usfirst.frc.team6341.robot2017.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Contains the NavX MXP
 * @author Dan Mulloy
 */
public class NavXSubsystem extends EagleSubsystem {
	private AHRS ahrs;

	public NavXSubsystem() {
		this.ahrs = new AHRS(SerialPort.Port.kMXP);
	}

	public double getAngle() {
		return ahrs.getAngle();
	}

	public double getYaw() {
		return ahrs.getYaw();
	}

	public double getAccelX() {
		return ahrs.getWorldLinearAccelX();
	}

	public double getAccelY() {
		return ahrs.getWorldLinearAccelY();
	}

	public void reset() {
		ahrs.reset();
		ahrs.zeroYaw();
	}

	@Override
	public void updateDashboard() {
		SmartDashboard.putBoolean("moving", ahrs.isMoving());
		SmartDashboard.putNumber("angle", ahrs.getAngle());
		SmartDashboard.putNumber("yaw", ahrs.getYaw());
		SmartDashboard.putNumber("velX", ahrs.getVelocityX());
		SmartDashboard.putNumber("velY", ahrs.getVelocityY());
		SmartDashboard.putNumber("velZ", ahrs.getVelocityZ());
	}
}