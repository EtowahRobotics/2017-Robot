/**
 * (c) 2017 Etowah Robotics
 */
package org.usfirst.frc.team6341.robot2017.commands;

import org.usfirst.frc.team6341.robot2017.Robot;
import org.usfirst.frc.team6341.robot2017.tasks.IterativeMotorTask;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 * Basic autonomous drive command for finding correction coefficient
 * @author Dan Mulloy
 */
// Distance will be based on starting position
// Time will be distance / velocity
public class AutoDriveCommand extends TimedCommand {
	static double TIME = 2.5; // Run for 2.5 seconds
	static double SPEED = 0.5; // ...at half speed
	static int WAIT_TIME = 100; // ...wait 100ms between iterations
	static int COOLDOWN_ITERS =  (int) (SPEED * 10); // ...and cool down at the end.

	public AutoDriveCommand() {
		super(TIME);
	}

	@Override
	public void execute() {
		Robot.drivetrain.autoDrive(SPEED, 0);
		sleep();
	}

	@Override
	public void end() {
		stopGracefully();
	}

	private void sleep() {
		try {
			Thread.sleep(WAIT_TIME);
		} catch (Throwable ex) {
			ex.printStackTrace();
		}
	}

	// Stop the robot gracefully without spinning out
	private void stopGracefully() {
		new IterativeMotorTask(SPEED, 0, 0.1, WAIT_TIME, (speed) -> Robot.drivetrain.rawDrive(speed, 0));

		/* double speed = SPEED;
		double delta = speed / COOLDOWN_ITERS;
		for (int i = 0; i < COOLDOWN_ITERS && speed >= 0; i++) {
			speed -= delta;
			Robot.drivetrain.rawDrive(speed, 0);
			sleep();
		} */
	}
}