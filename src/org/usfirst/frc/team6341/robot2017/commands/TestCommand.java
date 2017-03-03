/**
 * (c) 2017 Etowah Robotics
 */
package org.usfirst.frc.team6341.robot2017.commands;

import org.usfirst.frc.team6341.robot2017.Robot;
import org.usfirst.frc.team6341.robot2017.tasks.IterativeMotorTask;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 * Command structure for testing stuff
 * @author Dan Mulloy
 */
public class TestCommand extends TimedCommand {
	static double timeout = 10; // Run for 10 seconds
	static double speed = 0.75D; // ...at 75% speed.

	public TestCommand() {
		super(timeout);
	}

	@Override
	public void initialize() {
		Robot.initTest();
		new IterativeMotorTask(0, speed, 0.1, 100, (speed) -> Robot.test.power(speed));
	}

	@Override
	public void execute() {
		Robot.test.updateDashboard();
	}

	@Override
	public void end() {
		new IterativeMotorTask(speed, 0, 0.1, 100, (speed) -> Robot.test.power(speed));
	}
}