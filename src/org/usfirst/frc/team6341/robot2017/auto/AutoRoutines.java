/**
 * (c) 2017 Etowah Robotics
 */
package org.usfirst.frc.team6341.robot2017.auto;

import org.usfirst.frc.team6341.robot2017.auto.objective.DriveObjective;
import org.usfirst.frc.team6341.robot2017.auto.objective.GearObjective;
import org.usfirst.frc.team6341.robot2017.auto.objective.PreparationObjective;
import org.usfirst.frc.team6341.robot2017.auto.objective.RotationObjective;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;

/**
 * Contains the three supported autonomous routines
 * @author Dan Mulloy
 */
public class AutoRoutines {

	// TODO We need to do some real testing here
	// Replace rotation with curved driving
	private static final AutoRoutine ROUTINE_1 = new AutoRoutine()
			.add(new PreparationObjective())
			.add(new DriveObjective(0.5, 0, 1))
			.add(new RotationObjective(-45, 0.75))
			.add(new GearObjective(0.5, 0, 2))
			.add(new DriveObjective(-0.5, 0, 1))
			.add(new RotationObjective(45, 0.5))
			.add(new DriveObjective(0.5, 0, 1));
	public static final AutoRoutine ROUTINE_2 = new AutoRoutine()
			.add(new PreparationObjective())
			.add(new GearObjective(0.5, 0, 2));
	public static final AutoRoutine ROUTINE_3 = new AutoRoutine()
			.add(new PreparationObjective())
			.add(new DriveObjective(0.5, 0, 1))
			.add(new RotationObjective(45, 0.5))
			.add(new GearObjective(0.5, 0, 2))
			.add(new DriveObjective(-0.5, 0, 1))
			.add(new RotationObjective(-45, 0.5))
			.add(new DriveObjective(0.5, 0, 1));

	public static AutoRoutine getRoutine() {
		int station = DriverStation.getInstance().getLocation();
		Alliance alliance = DriverStation.getInstance().getAlliance();

		switch (station) {
		case 1: return alliance == Alliance.Red ? ROUTINE_1 : ROUTINE_3;
		case 2: return ROUTINE_2;
		case 3: return alliance == Alliance.Red ? ROUTINE_3 : ROUTINE_1;
		}

		throw new IllegalStateException("Invalid station: " + station);
	}

	// For testing
	/* public static AutoRoutine getRoutine() {
		return new AutoRoutine()
				.add(new PreparationObjective())
				.add(new DriveObjective(0.5, 10));
	} */
}
