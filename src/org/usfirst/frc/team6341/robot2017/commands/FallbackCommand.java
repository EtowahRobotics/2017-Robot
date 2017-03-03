/**
 * (c) 2017 Etowah Robotics
 */
package org.usfirst.frc.team6341.robot2017.commands;

import org.usfirst.frc.team6341.robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Falls back to the default robot drive implementation
 * @author Dan Mulloy
 */
public class FallbackCommand extends Command {

	@Override
	public void execute() {
		Robot.swapDrivetrains();
	}
	
	@Override
	protected boolean isFinished() {
		return true; // Single execution
	}
}