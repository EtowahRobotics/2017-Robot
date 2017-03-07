package org.usfirst.frc.team6341.robot2017.vision;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.vision.VisionPipeline;

public abstract class EaglePipeline implements VisionPipeline {
	protected List<Double> area = new ArrayList<>();

	public List<Double> getArea() {
		return area;
	}

	public abstract int getCamera();
	public abstract String getKey();
}