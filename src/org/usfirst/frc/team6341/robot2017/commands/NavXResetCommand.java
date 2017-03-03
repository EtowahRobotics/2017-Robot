package org.usfirst.frc.team6341.robot2017.commands;

import org.usfirst.frc.team6341.robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class NavXResetCommand extends Command {

	@Override
	public void execute() {
		Robot.navX.reset();
	}

	@Override
	public boolean isFinished() {
		return true; // Single execution
	}
}
