/**
 * (c) 2017 Etowah Robotics
 */
package org.usfirst.frc.team6341.robot2017;

import java.util.Timer;

import org.usfirst.frc.team6341.robot2017.auto.StandardAutoCommand;
import org.usfirst.frc.team6341.robot2017.auto.VisionTracking;
import org.usfirst.frc.team6341.robot2017.commands.AutoDriveCommand;
import org.usfirst.frc.team6341.robot2017.commands.TeleopDriveCommand;
import org.usfirst.frc.team6341.robot2017.commands.TeleopTurretCommand;
import org.usfirst.frc.team6341.robot2017.subsystems.NavXSubsystem;
import org.usfirst.frc.team6341.robot2017.subsystems.PulleySubsystem;
import org.usfirst.frc.team6341.robot2017.subsystems.TestSubsystem;
import org.usfirst.frc.team6341.robot2017.subsystems.TurretSubsystem;
import org.usfirst.frc.team6341.robot2017.subsystems.drive.CustomDrive;
import org.usfirst.frc.team6341.robot2017.subsystems.drive.Drivetrain;
import org.usfirst.frc.team6341.robot2017.subsystems.drive.EagleDrive;

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

	// ---- Controls
	public static OI oi;

	// ---- Drivetrain
	public static Drivetrain drivetrain;
	Command driveCommand;

	// ---- NavX
	public static NavXSubsystem navX;

	// ---- Turret
	public static TurretSubsystem turret;
	public static PulleySubsystem pulleys;
	Command turretCommand;

	// ---- Testing
	public static TestSubsystem test;

	// ---- Autonomous
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	public static Timer timer = new Timer();

	/**
	 * Initializes testing code if it hasn't been initialized already
	 */
	public static void initTest() {
		if (test == null) {
			test = new TestSubsystem();
		}
	}

	/**
	 * Swaps drivetrain code on the fly. Not recommended.
	 */
	public static void swapDrivetrains() {
		drivetrain.release();
		if (drivetrain instanceof EagleDrive) {
			drivetrain = new CustomDrive();
		} else {
			drivetrain = new EagleDrive();
		}
	}

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		oi = new OI();

		drivetrain = new CustomDrive();
		driveCommand = new TeleopDriveCommand();

		turret = new TurretSubsystem();
		pulleys = new PulleySubsystem();
		turretCommand = new TeleopTurretCommand();

		navX = new NavXSubsystem();

		VisionTracking.init();

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
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();

		// Start the drivetrain
		driveCommand.start();
		// Start the turret
		turretCommand.start();
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
