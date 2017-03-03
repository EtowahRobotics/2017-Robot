/**
 * (c) 2017 Etowah Robotics
 */
package org.usfirst.frc.team6341.robot2017.subsystems.drive;

import org.usfirst.frc.team6341.robot2017.RobotMap;
import org.usfirst.frc.team6341.robot2017.subsystems.EagleSubsystem;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Base drivetrain code shared between the two implementations
 * @author Dan Mulloy
 */
public abstract class Drivetrain extends EagleSubsystem {
	// TODO This needs to be updated
	public static final double CORRECTION = 0.0000075;

	protected Spark frontLeft;
	protected Spark backLeft;

	protected Spark frontRight;
	protected Spark backRight;

	protected double move   = 0;
	protected double rotate = 0;

	public Drivetrain() {
		this.frontLeft = new Spark(RobotMap.frontLeft);
		this.backLeft = new Spark(RobotMap.backLeft);
		this.frontRight = new Spark(RobotMap.frontRight);
		this.backRight = new Spark(RobotMap.backRight);
	}

	public abstract void teleopDrive(Joystick stick);

	public void autoDrive(double move, double curve) {
		rawDrive(move, curve + CORRECTION);
	}

	public abstract void rawDrive(double move, double curve);

	public void power(double left, double right) {
		left = limit(left);
		right = -limit(right); // Right motors need to be reversed

		frontLeft.set(left);
		backLeft.set(left);
		frontRight.set(right);
		backRight.set(right);
	}

	protected double limit(double value) {
		return Math.min(1, Math.max(value, -1));
	}

	public void release() {
		frontLeft.free();
		backLeft.free();
		frontRight.free();
		backRight.free();
	}

	@Override
	public void updateDashboard() {
		SmartDashboard.putNumber("move", move);
		SmartDashboard.putNumber("rotate", rotate);
		SmartDashboard.putNumber("frontLeft", frontLeft.get());
		SmartDashboard.putNumber("backLeft", backLeft.get());
		SmartDashboard.putNumber("frontRight", frontRight.get());
		SmartDashboard.putNumber("backRight", backRight.get());
	}
}