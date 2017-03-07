/**
 * (c) 2017 Etowah Robotics
 */
package org.usfirst.frc.team6341.robot2017.auto;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team6341.robot2017.auto.objective.AutoObjective;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Boilerplate code for autonomous routines
 * @author Dan Mulloy
 */
public class AutoRoutine {
	private List<AutoObjective> objectives = new ArrayList<>();
	private AutoObjective current = null;
	private int index = 0;

	public AutoRoutine add(AutoObjective objective) {
		objectives.add(objective);
		return this;
	}

	public void start() {
		this.index = 0;
		this.current = objectives.get(index);
		current.start();
	}

	public void next() {
		if (!hasNext()) {
			throw new IllegalStateException("No more objectives to execute!");
		}

		AutoObjective previous = current;

		index++;
		this.current = objectives.get(index);
		current.setPrevious(previous);
		SmartDashboard.putString("autoRoutine", current.getClass().getSimpleName());
		current.start();
	}

	public void periodic() {
		if (!isFinished())
			current.periodic();
	}

	public boolean isFinished() {
		boolean finished = current != null && current.isFinished();
		SmartDashboard.putBoolean("autoFinished", finished);
		return finished;
	}

	public boolean hasNext() {
		return index < objectives.size();
	}
}