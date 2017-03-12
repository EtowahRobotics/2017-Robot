/**
 * (c) 2017 Etowah Robotics
 */
package org.usfirst.frc.team6341.robot2017.auto;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Standard autonomous command that goes through the routine until completion
 * @author Dan Mulloy
 */
public class StandardAutoCommand extends Command {
	private AutoRoutine routine;
	private boolean finished;

	@Override
	public void start() {
		this.routine = AutoRoutines.getRoutine();
		routine.start();
		super.start();
	}

	@Override
	public void execute() {
		try {
			if (routine.isFinished()) {
				if (routine.hasNext()) {
					routine.next();
				} else {
					finished = true;
				}
			} else {
				routine.periodic();
			}
		} catch (Throwable ex) {
			// Autonomous failed, don't screw teleop
			finished = true;
			cancel();
			return;
		}
	}

	@Override
	protected boolean isFinished() {
		return finished;
	}
}