/**
 * (c) 2017 Etowah Robotics
 */
package org.usfirst.frc.team6341.robot2017.auto;

import org.usfirst.frc.team6341.robot2017.auto.objective.*;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;

/**
 * Contains the three supported autonomous routines
 * @author Dan Mulloy
 */
public class AutoRoutines {
	private static final AutoRoutine ROUTINE_1 = new AutoRoutine()
			.add(new PreparationObjective())
			.add(new DriveObjective(0.75, 1))
			.add(new RotationObjective(-45, 0.75))
			.add(new GearObjective(0.75))
			.add(new DriveObjective(-0.75, 1))
			.add(new RotationObjective(45, 0.75))
			.add(new DriveObjective(0.75, 2));
	public static final AutoRoutine ROUTINE_2 = new AutoRoutine()
			.add(new PreparationObjective())
			.add(new GearObjective(0.75));
	public static final AutoRoutine ROUTINE_3 = new AutoRoutine()
			.add(new PreparationObjective())
			.add(new RotationObjective(45, 0.75))
			.add(new GearObjective(0.75))
			.add(new DriveObjective(-0.75, 1))
			.add(new RotationObjective(-45, 0.75))
			.add(new DriveObjective(0.75, 2));

	/* private static final AutoRoutine ROUTINE_1 = new AutoRoutine()
			.add(new PreparationObjective())
			.add(new DriveObjective(0.5, 0.5))
			.add(new RotationObjective(135, 0.5))
			.add(new HopperObjective(-1))
			.add(new DriveObjective(1, 0.1))
			.add(new RotationObjective(-90, 0.5))
			.add(new GearObjective(1))
			.add(new DriveObjective(-1, 0.1))
			.add(new RotationObjective(-90, 0.5))
			.add(new DriveObjective(1, 0.5))
			.add(new RotationObjective(-45, 0.5))
			.add(new AlignObjective(1))
			.add(new ShootObjective());
	private static final AutoRoutine ROUTINE_2 = new AutoRoutine()
			.add(new PreparationObjective())
			.add(new DriveObjective(1, 0.1))
			.add(new RotationObjective(135, 0.5))
			.add(new HopperObjective(-1))
			.add(new RotationObjective(-45, 0.5))
			.add(new GearObjective(-1))
			.add(new RotationObjective(135, 0.5))
			.add(new AlignObjective(1))
			.add(new ShootObjective());
	private static final AutoRoutine ROUTINE_2 = new AutoRoutine()
			.add(new PreparationObjective())
			.add(new GearObjective(1));
	private static final AutoRoutine ROUTINE_3 = new AutoRoutine()
			.add(new PreparationObjective())
			.add(new DriveObjective(1, 0.1))
			.add(new RotationObjective(135, 0.5))
			.add(new HopperObjective(-1))
			.add(new RotationObjective(-45, 0.5))
			.add(new GearObjective(1))
			.add(new RotationObjective(135, 0.5))
			.add(new AlignObjective(1))
			.add(new ShootObjective())
			.add(new RotationObjective(135, 0.5))
			.add(new DriveObjective(1, 2)); */

	public static AutoRoutine getRoutine() {
		Alliance alliance = DriverStation.getInstance().getAlliance();
		switch (DriverStation.getInstance().getLocation()) {
		case 1: return alliance == Alliance.Red ? ROUTINE_1 : ROUTINE_3;
		case 2: return ROUTINE_2;
		case 3: return alliance == Alliance.Red ? ROUTINE_3 : ROUTINE_1;
		}

		throw new IllegalStateException("Lmao what");
	}

	/* public static AutoRoutine getRoutine() {
		return new AutoRoutine()
				.add(new PreparationObjective())
				.add(new DriveObjective(0.5, 10));
	} */
}
