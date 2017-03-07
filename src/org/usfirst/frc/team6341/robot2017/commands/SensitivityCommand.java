/**
 * (c) 2017 Etowah Robotics
 */
package org.usfirst.frc.team6341.robot2017.commands;

import org.usfirst.frc.team6341.robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Changes the sensitivity on the controls
 * @author Dan Mulloy
 */
public class SensitivityCommand extends Command {
	private int delta = 1;

	public SensitivityCommand(boolean increase) {
		if (!increase) delta = -delta;
	}

	@Override
	public void execute() {
		Robot.drivetrain.changeSensitivity(delta);
	}

	@Override
	protected boolean isFinished() {
		return true; // Single execution
	}

}
