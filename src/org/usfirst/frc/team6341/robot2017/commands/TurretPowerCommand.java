package org.usfirst.frc.team6341.robot2017.commands;

import edu.wpi.first.wpilibj.command.Command;

public class TurretPowerCommand extends Command {
	/* private boolean increase;

	public TurretPowerCommand(boolean increase) {
		this.increase = increase;
	}

	@Override
	public void execute() {
		if (increase)
			Robot.turret.increasePower();
		else
			Robot.turret.decreasePower();
	} */

	@Override
	public boolean isFinished() {
		return true; // Single execution
	}

}
