/**
 * (c) 2017 Etowah Robotics
 */
package org.usfirst.frc.team6341.robot2017.subsystems.drive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 * Standard drivetrain implementation using WPILib's RobotDrive
 * @author Dan Mulloy
 */
public class WPIDrive extends Drivetrain {
	private RobotDrive drive;

	public WPIDrive() {
		super();

		this.drive = new RobotDrive(frontLeft, backLeft, frontRight, backRight);
		drive.setSafetyEnabled(true);
	}

	@Override
	public void teleopDrive(Joystick stick) {
		this.move   = -stick.getY();
		this.rotate = -stick.getTwist();

		drive.arcadeDrive(move, rotate, true);
		updateDashboard();
	}

	@Override
	public void rawDrive(double move, double curve) {
		this.move = move;
		this.rotate = curve;

		drive.drive(move, curve);
		updateDashboard();
	}
}