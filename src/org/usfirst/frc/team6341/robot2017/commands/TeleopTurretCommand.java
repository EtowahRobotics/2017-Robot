/**
 * (c) 2017 Etowah Robotics
 */
package org.usfirst.frc.team6341.robot2017.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Basic repeating turret command. This command never stops executing, though
 * nothing /should/ happen if the shooter joystick is idle.
 * @author Dan Mulloy
 */
public class TeleopTurretCommand extends Command {
	/* private static final double DEAD_ZONE = 0.3; // 0.3^2 == 0.09 < 0.1

	@Override
	public void start() {
		Robot.turret.spinUp();
		super.start();
	}

	@Override
	public void execute() {
		// Turret rotation
		double pitch = -Robot.oi.getPitch();
		SmartDashboard.putNumber("pitch", pitch);
		if (pitch >= DEAD_ZONE || pitch <= -DEAD_ZONE) {
			Robot.turret.rotate(pitch);
		} else {
			Robot.turret.rotate(0);
		}

		// Shooting
		double trigger = Robot.oi.getTrigger();
		if (trigger >= DEAD_ZONE || trigger <= -DEAD_ZONE) {
			Robot.pulleys.start();
		} else {
			Robot.pulleys.stop();
		}
	}

	@Override
	public void end() {
		Robot.turret.slowDown();
		Robot.turret.setPower(0.5D);
	} */

	@Override
	protected boolean isFinished() {
		return false; // Continuously executes
	}
}