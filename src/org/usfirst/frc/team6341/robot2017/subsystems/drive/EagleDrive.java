/**
 * (c) 2017 Etowah Robotics
 */
package org.usfirst.frc.team6341.robot2017.subsystems.drive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.MotorSafety;
import edu.wpi.first.wpilibj.MotorSafetyHelper;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Etowah's custom drivetrain with variable sensitivity
 * @author Dan Mulloy
 */
public class EagleDrive extends Drivetrain implements MotorSafety {
	private int factor = 3;
	private boolean even = false;

	private double expirationTime = 0.5;
	private double sensitivity = 0.5;

	private MotorSafetyHelper safetyHelper;

	public EagleDrive() {
		super();

		setupSafety();
		power(0, 0);
	}

	@Override
	public void updateDashboard() {
		super.updateDashboard();
		SmartDashboard.putNumber("sensitivity", factor);
		SmartDashboard.putBoolean("custom", true);
	}

	@Override
	public void changeSensitivity(int delta) {
		factor = Math.max(1, Math.min(10, factor + delta));
		even = factor % 2 == 0;
	}

	public void teleopDrive(Joystick stick) {
		// The front of the robot is the part with the gear
		// Pushing the joystick forward should move the robot forward

		double move = -stick.getY();
		this.move = move;

		double rotate = -stick.getTwist();
		this.rotate = rotate;

		arcadeDrive(move, rotate, true);
		updateDashboard();
	}

	public void autoDrive(double mag, double curve) {
		rawDrive(mag, curve + CORRECTION);
	}

	// Adapted from RobotDrive
	public void rawDrive(double output, double curve) {
		double left, right;

		if (curve > 0) { // Curving to the right
			double value = Math.log(curve);
			double ratio = (value - sensitivity) / (value + sensitivity);
			if (ratio == 0) ratio = .0000000001; // Why this number?
			left = output / ratio;
			right = output;
		} else if (curve < 0) { // Curving to the left
			double value = Math.log(-curve);
			double ratio = (value - sensitivity) / (value + sensitivity);
			if (ratio == 0) ratio = .0000000001; // Why this number?
			left = output;
			right = output / ratio;
		} else { // Straight shot
			left = right = output;
		}

		power(left, right);
		updateDashboard();
	}

	private void arcadeDrive(double move, double rotate, boolean lowerSensitivity) {
		double left, right;

		if (move == 0 && rotate == 0) { // No movement
			power(0, 0);
			return;
		}

		double rawMove = move;
		double rawRotate = rotate;

		if (lowerSensitivity) { // Lower sensitivity for drive team cuz they need help
			move = Math.pow(move, factor);
			rotate = Math.pow(rotate, factor);

			if (even) { // We may have to fix signs
				if (rawMove < 0) move = -move;
				if (rawRotate < 0) rotate =-rotate;
			}
		}

		if (rotate == 0) { // Moving straight
			power(move, move);
			return;
		}

		if (move > 0.0) {
			if (rotate > 0.0) {
				left = move - rotate;
				right = Math.max(move, rotate);
			} else {
				left = Math.max(move, -rotate);
				right = move + rotate;
			}
		} else {
			if (rotate > 0.0) {
				left = -Math.max(-move, rotate);
				right = move + rotate;
			} else {
				left = move - rotate;
				right = -Math.max(-move, -rotate);
			}
		}

		/* if (move > 0) { // Moving forward
			if (rotate > 0) { // Forward & to the right
				left = move + rotate;
				right = move;
			} else { // Forward & to the left
				left = move;
				right = move - rotate;
			}
		} else if (move < 0) { // Moving backward
			if (rotate > 0) { // Backward & to the left
				left = move - rotate;
				right = move;
			} else { // Backward & to the right
				left = move;
				right = move + rotate;
			}
		} else {
			// Rotating in place
			left = rotate;
			right = -rotate;
		} */

		power(left, right);
	}

	@Override
	public void power(double left, double right) {
		left = limit(left);
		right = -limit(right); // Right motors need to be reversed

		frontLeft.set(left);
		backLeft.set(left);
		frontRight.set(right);
		backRight.set(right);

		if (safetyHelper != null) safetyHelper.feed();
	}

	// ---- Safety

	private void setupSafety() {
		safetyHelper = new MotorSafetyHelper(this);
		safetyHelper.setExpiration(expirationTime);
		safetyHelper.setSafetyEnabled(true);
	}

	@Override
	public void stopMotor() {
		if (frontLeft != null) frontLeft.stopMotor();
		if (backLeft != null) backLeft.stopMotor();
		if (frontRight != null) frontRight.stopMotor();
		if (backRight != null) backRight.stopMotor();
		if (safetyHelper != null) safetyHelper.feed();
	}

	@Override
	public void setExpiration(double timeout) {
		safetyHelper.setExpiration(timeout);
	}

	@Override
	public double getExpiration() {
		return safetyHelper.getExpiration();
	}

	@Override
	public boolean isAlive() {
		return safetyHelper.isAlive();
	}

	@Override
	public void setSafetyEnabled(boolean enabled) {
		safetyHelper.setSafetyEnabled(true);
	}

	@Override
	public boolean isSafetyEnabled() {
		return safetyHelper.isSafetyEnabled();
	}

	@Override
	public String getDescription() {
		return "Eagle Drive";
	}
}