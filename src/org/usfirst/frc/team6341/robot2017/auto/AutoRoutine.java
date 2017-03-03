/**
 * (c) 2017 Etowah Robotics
 */
package org.usfirst.frc.team6341.robot2017.auto;

import java.util.ArrayList;
import java.util.List;

import org.usfirst.frc.team6341.robot2017.auto.objective.AutoObjective;

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
		current.execute();
	}

	public void next() {
		if (!hasNext()) {
			throw new IllegalStateException("No more objectives to execute!");
		}

		AutoObjective previous = current;

		index++;
		this.current = objectives.get(index);
		current.setPrevious(previous);
		current.execute();
	}

	public boolean isFinished() {
		return current != null && current.isFinished();
	}

	public boolean hasNext() {
		return index < objectives.size();
	}
}