package org.usfirst.frc.team6341.robot2017;

import org.usfirst.frc.team6341.robot2017.commands.NavXResetCommand;
import org.usfirst.frc.team6341.robot2017.commands.SensitivityCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

// TODO Add some routines
public class OI {
	int driveStickPort = 0;
	Joystick driveStick = new Joystick(driveStickPort);

	int resetButtonNo = 2; // Thumb button
	int increaseSensitivityNo = 5; // Left of POV
	int decreaseSensitivityNo = 6; // Right of POV

	public OI() {
		JoystickButton resetButton = new JoystickButton(driveStick, resetButtonNo);
		resetButton.whenPressed(new NavXResetCommand());

		JoystickButton increaseSensitivity = new JoystickButton(driveStick, increaseSensitivityNo);
		increaseSensitivity.whenPressed(new SensitivityCommand(true));

		JoystickButton decreaseSensitivity = new JoystickButton(driveStick, decreaseSensitivityNo);
		decreaseSensitivity.whenPressed(new SensitivityCommand(false));
	}

	public Joystick getDriveStick() {
		return driveStick;
	}

	public boolean getClimbTrigger() {
		return driveStick.getTrigger();
	}
}