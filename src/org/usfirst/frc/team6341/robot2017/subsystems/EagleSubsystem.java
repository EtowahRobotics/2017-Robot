/**
 * (c) 2017 Etowah Robotics
 */
package org.usfirst.frc.team6341.robot2017.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Basic subsystem class
 * @author Dan Mulloy
 */
public abstract class EagleSubsystem extends Subsystem {

	public abstract void updateDashboard();

	@Override
	protected void initDefaultCommand() { }
}