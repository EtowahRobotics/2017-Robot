/**
 * (c) 2017 Etowah Robotics
 */
package org.usfirst.frc.team6341.robot2017.commands;

import org.usfirst.frc.team6341.robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Basic repeating teleop drive command. The command never 'stops' executing,
 * though nothing /should/ happen if the joystick is not moving.
 * @author Dan Mulloy
 */
public class TeleopDriveCommand extends Command {

	@Override
	public void execute() {
		Robot.drivetrain.teleopDrive(Robot.oi.getDriveStick());
	}

	@Override
	protected boolean isFinished() {
		return false; // Continuous execution
	}
}