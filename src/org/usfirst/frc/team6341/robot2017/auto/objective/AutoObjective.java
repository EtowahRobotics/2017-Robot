/**
 * (c) 2017 Etowah Robotics
 */
package org.usfirst.frc.team6341.robot2017.auto.objective;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;

/**
 * An objective in autonomous
 * @author Dan Mulloy
 */
public abstract class AutoObjective {
	protected boolean finished;
	protected AutoObjective previous;

	public void setPrevious(AutoObjective previous) {
		this.previous = previous;
	}

	/**
	 * Executes this objective. Only called once
	 */
	public abstract void execute();

	/**
	 * Called just before the objective finishes. Use this for delays, etc.
	 */
	public void preFinish() {
	}
	
	/**
	 * Marks this objective as completed
	 */
	public final void finish() {
		if (! finished) {
			preFinish();
			finished = true;
		}
	}

	/**
	 * Whether or not this objective is finished executing
	 * @return True if it is, false if not
	 */
	public boolean isFinished() {
		return finished;
	}

	// ---- Useful Methods

	/**
	 * Returns the alliance we are currently on
	 * @return The alliance
	 */
	public Alliance getAlliance() {
		return DriverStation.getInstance().getAlliance();
	}
}