package org.usfirst.frc.team6341.robot2017;

import org.usfirst.frc.team6341.robot2017.commands.NavXResetCommand;
import org.usfirst.frc.team6341.robot2017.commands.SensitivityCommand;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

// TODO We may remap the controls entirely, tbd
public class OI {
	int driveStickPort = 0;
	public Joystick driveStick = new Joystick(driveStickPort);

	int xboxPort = 1;
	public XboxController shootController = new XboxController(xboxPort);

	// ---- Shooter mappings
	private Hand pitchHand = Hand.kLeft;
	private Hand trigHand = Hand.kRight;

	int resetButtonNo = 2;
	// int testButtonNo = 7;
	// int fallbackButtonNo = 9;
	int increaseSensitivityNo = 5;
	int decreaseSensitivityNo = 6;

	int leftBumperNo = 5;
	int rightBumperNo = 6;

	public OI() {
		JoystickButton resetButton = new JoystickButton(driveStick, resetButtonNo);
		resetButton.whenPressed(new NavXResetCommand());

		JoystickButton increaseSensitivity = new JoystickButton(driveStick, increaseSensitivityNo);
		increaseSensitivity.whenPressed(new SensitivityCommand(true));

		JoystickButton decreaseSensitivity = new JoystickButton(driveStick, decreaseSensitivityNo);
		decreaseSensitivity.whenPressed(new SensitivityCommand(false));

		/*
		JoystickButton leftBumper = new JoystickButton(shootController, leftBumperNo);
		leftBumper.whenPressed(new TurretPowerCommand(false));
		
		JoystickButton rightBumper = new JoystickButton(shootController, rightBumperNo);
		rightBumper.whenPressed(new TurretPowerCommand(true));

		JoystickButton testButton = new JoystickButton(driveStick, testButtonNo);
		testButton.whenPressed(new TestCommand());

		JoystickButton fallbackButton = new JoystickButton(driveStick, fallbackButtonNo);
		fallbackButton.whenPressed(new FallbackCommand());
		*/
	}

	public double getPitch() {
		return shootController.getX(pitchHand);
	}

	public double getTrigger() {
		return shootController.getTriggerAxis(trigHand);
	}

	public boolean getClimbTrigger() {
		return driveStick.getRawButton(7);
	}

	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
}
