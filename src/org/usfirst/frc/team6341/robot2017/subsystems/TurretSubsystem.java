/**
 * (c) 2017 Etowah Robotics
 */
package org.usfirst.frc.team6341.robot2017.subsystems;

import org.usfirst.frc.team6341.robot2017.RobotMap;
import org.usfirst.frc.team6341.robot2017.tasks.IterativeMotorTask;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Turret subsystem containing a motor for rotation and fly wheels for shooting.
 * @author Dan Mulloy
 */
public class TurretSubsystem extends EagleSubsystem {
	// Fixed angle for projectile calculations
	// TODO this needs to be updated
	private static final double angle = 75.0D;

	private double power;
	private boolean rotating;

	private Spark rotation;
	private CANTalon flyWheels;

	public TurretSubsystem() {
		rotation = new Spark(RobotMap.turretRotation);
		flyWheels = new CANTalon(RobotMap.flyWheels);

		rotation.set(0);
		flyWheels.set(0);

		this.power = 0.5D;
		this.rotating = false;

		updateDashboard();
	}

	public void spinUp() {
		new IterativeMotorTask(0, power, 0.1, 100, (speed) -> flyWheels.set(speed));
		updateDashboard();
	}

	public void slowDown() {
		new IterativeMotorTask(power, 0, 0.1, 100, (speed) -> flyWheels.set(speed));
		updateDashboard();
	}

	public void decreasePower() {
		if (power > 0.0D) {
			power = Math.max(power - 0.1D, 0.0D); // Floor it at 0
		}

		setPower();
	}

	public void increasePower() {
		if (power < 1.0D) {
			power = Math.min(power + 0.1D, 1.0D); // Cap it at 1
		}

		setPower();
	}

	private void setPower() {
		flyWheels.set(power);
		updateDashboard();
	}

	public void setPower(double power) {
		this.power = power;
	}

	public void setYaw(double yaw) {
		// Encoders?
	}

	public void rotate(double power) {
		if (power == 0) {
			if (rotating) {
				rotation.set(0);
				rotating = false;
				updateDashboard();
			}

			return;
		}

		// Square the inputs for lower sensitivity
		if (power > 0) power *= power;
		else power *= -power;

		rotation.set(power);
		rotating = true;
		updateDashboard();
	}

	@Override
	public void updateDashboard() {
		SmartDashboard.putNumber("turretPower", power);
		SmartDashboard.putNumber("turretPitch", angle);
		SmartDashboard.putNumber("turretSpeed", flyWheels.get());
		SmartDashboard.putBoolean("turretRotating", rotating);
	}
}