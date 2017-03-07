package org.usfirst.frc.team6341.robot2017.commands;

import org.usfirst.frc.team6341.robot2017.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimbCommand extends Command {

	@Override
	public void execute() {
		if (Robot.oi.getClimbTrigger()) {
			Robot.climber.power(1);
		} else {
			Robot.climber.power(0);
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

}
