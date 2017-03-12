/**
 * (c) 2017 Etowah Robotics
 */
package org.usfirst.frc.team6341.robot2017;

import org.usfirst.frc.team6341.robot2017.auto.AutoDriveCommand;
import org.usfirst.frc.team6341.robot2017.auto.StandardAutoCommand;
import org.usfirst.frc.team6341.robot2017.commands.ClimbCommand;
import org.usfirst.frc.team6341.robot2017.commands.TeleopDriveCommand;
import org.usfirst.frc.team6341.robot2017.subsystems.ClimberSubsystem;
import org.usfirst.frc.team6341.robot2017.subsystems.NavXSubsystem;
import org.usfirst.frc.team6341.robot2017.subsystems.drive.Drivetrain;
import org.usfirst.frc.team6341.robot2017.subsystems.drive.EagleDrive;
import org.usfirst.frc.team6341.robot2017.vision.VisionTracking;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	public static final int TEAM = 6341;

	private static Robot instance;
	public static Robot getInstance() { return instance; }

	public Robot() {
		super();
		instance = this;
	}

	// ---- Controls
	public static OI oi;

	// ---- Drivetrain
	public static Drivetrain drivetrain;
	Command driveCommand;

	// ---- Climbing Mech
	public static ClimberSubsystem climber;
	Command climbCommand;

	// ---- NavX
	public static NavXSubsystem navX;

	// ---- Autonomous
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();

		drivetrain = new EagleDrive();
		driveCommand = new TeleopDriveCommand();

		climber = new ClimberSubsystem();
		climbCommand = new ClimbCommand();

		navX = new NavXSubsystem();

		VisionTracking.init();

		SmartDashboard.putString("alliance", DriverStation.getInstance().getAlliance().name());
		SmartDashboard.putNumber("station", DriverStation.getInstance().getLocation());

		chooser.addDefault("Standard", new StandardAutoCommand());
		chooser.addObject("DriveTest", new AutoDriveCommand());
		SmartDashboard.putData("Auto mode", chooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		Timers.cancelAll();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		Timers.cancelAuto();

		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();

		// Start the drivetrain
		driveCommand.start();
		// Start the turret
		// turretCommand.start();
		// Start the climber
		climbCommand.start();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		navX.updateDashboard();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
