/**
 * (c) 2017 Etowah Robotics
 */
package org.usfirst.frc.team6341.robot2017.auto;

import org.usfirst.frc.team6341.robot2017.Robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.tables.ITable;
import edu.wpi.first.wpilibj.tables.ITableListener;

/**
 * Handles vision tracking input from the Raspberry PI
 * @author Dan Mulloy
 */
public class VisionTracking {
	public static final String GRIP = "GRIP/";
	public static final String redBoilerTarget = GRIP + "target_boiler_red";
	public static final String blueBoilerTarget = GRIP + "target_boiler_blue";
	public static final String airshipTarget = GRIP + "target_airship";
	public static final String highGoalTarget = GRIP + "target_goal_high";

	public static void init() {
		// Initialize the network table
		NetworkTable.setTeam(Robot.TEAM);
		NetworkTable.initialize();

		CameraServer.getInstance().startAutomaticCapture("FrontCam", 0);
		CameraServer.getInstance().startAutomaticCapture("TurretCam", 1);
	}

	public static void listen(String tableName, VisionTargetListener callback) {
		ITable table = NetworkTable.getTable(tableName);
		table.addTableListener(new ITableListener() {
			@Override
			public void valueChanged(ITable source, String key, Object value, boolean isNew) {
				callback.onTargetFound(new VisionTargetEvent(source));
			}
		}, true);
	}

	public static interface VisionTargetListener {
		void onTargetFound(VisionTargetEvent event);
	}

	public static class VisionTargetEvent {
		public final double[] area;
		public final double[] centerX;
		public final double[] centerY;
		public final double[] width;
		public final double[] height;
		public final double[] solidity;

		private VisionTargetEvent(ITable table) {
			this.area = table.getNumberArray("area", new double[0]);
			this.centerX = table.getNumberArray("centerX", new double[0]);
			this.centerY = table.getNumberArray("centerY", new double[0]);
			this.width = table.getNumberArray("width", new double[0]);
			this.height = table.getNumberArray("height", new double[0]);
			this.solidity = table.getNumberArray("solidity", new double[0]);
		}
	}
}