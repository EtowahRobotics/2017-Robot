/**
 * (c) 2017 Etowah Robotics
 */
package org.usfirst.frc.team6341.robot2017.vision;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.opencv.core.Mat;
import org.usfirst.frc.team6341.robot2017.Robot;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * Handles vision tracking input from the Raspberry PI
 * @author Dan Mulloy
 */
public class VisionTracking {
	private static boolean running = false;
	private static List<EaglePipeline> pipelines = new ArrayList<>();
	private static Map<String, List<VisionTargetListener>> listeners = new HashMap<>();

	private static UsbCamera frontCam;
	private static UsbCamera turretCam;

	public static final String GRIP = ""; // "GRIP/";
	public static final String redBoilerTarget = GRIP + "target_boiler_red";
	public static final String blueBoilerTarget = GRIP + "target_boiler_blue";
	public static final String airshipTarget = GRIP + "target_airship";
	public static final String highGoalTarget = GRIP + "target_goal_high";

	public static void init() {
		// Initialize the network table
		NetworkTable.setTeam(Robot.TEAM);
		NetworkTable.initialize();

		frontCam = CameraServer.getInstance().startAutomaticCapture("FrontCam", 0);
		turretCam = CameraServer.getInstance().startAutomaticCapture("TurretCam", 1);

		running = true;
	}

	public static void tick() {
		if (!running) return;

		for (EaglePipeline pipeline : pipelines) {
			List<VisionTargetListener> list = listeners.get(pipeline.getKey());
			if (list == null || list.isEmpty()) continue;
			
			Mat mat = new Mat();
			CvSink sink = null;
			if (pipeline.getCamera() == 0) {
				sink = CameraServer.getInstance().getVideo(frontCam);
			} else {
				sink = CameraServer.getInstance().getVideo(turretCam);
			}
			sink.grabFrame(mat);
			pipeline.process(mat);

			List<Double> area = pipeline.getArea();
			if (!area.isEmpty()) {
				for (VisionTargetListener listener : list) {
					listener.onTargetFound(new VisionTargetEvent(area));
				}
			}
		}
	}

	public static void stop() {
		running = false;
		pipelines.clear();
	}

	public static void listen(String tableName, VisionTargetListener callback) {
		List<VisionTargetListener> list = listeners.get(tableName);
		if (list == null) list = new ArrayList<>();
		list.add(callback);
		listeners.put(tableName, list);
		
		/* ITable table = NetworkTable.getTable(tableName);
		table.addTableListener(new ITableListener() {
			@Override
			public void valueChanged(ITable source, String key, Object value, boolean isNew) {
				callback.onTargetFound(new VisionTargetEvent(source));
			}
		}, true); */
	}

	public static interface VisionTargetListener {
		void onTargetFound(VisionTargetEvent event);
	}

	public static class VisionTargetEvent {
		public final Double[] area;
	
		public VisionTargetEvent(List<Double> area) {
			this.area = area.toArray(new Double[area.size()]);
		}

		/* public final double[] centerX;
		public final double[] centerY;
		public final double[] width;
		public final double[] height;
		public final double[] solidity;

		/* private VisionTargetEvent(ITable table) {
			this.area = table.getNumberArray("area", new double[0]);
			this.centerX = table.getNumberArray("centerX", new double[0]);
			this.centerY = table.getNumberArray("centerY", new double[0]);
			this.width = table.getNumberArray("width", new double[0]);
			this.height = table.getNumberArray("height", new double[0]);
			this.solidity = table.getNumberArray("solidity", new double[0]);
		} */
	}
}