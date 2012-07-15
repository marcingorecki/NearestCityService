package net.mgorecki.nearestcity.service.distance;

import java.text.DecimalFormat;

public class DistanceFormatter {
	public static String prettyMetricFromMeters(double meters) {
		String pretty;

		if (meters > 600) {
			DecimalFormat df = new DecimalFormat("####0.00");
			pretty = df.format(meters / 1000) + " km";
		} else {
			DecimalFormat df = new DecimalFormat("###0");
			pretty = df.format(meters) + " m";
		}
		return pretty;
	}

	public static String prettyMilesFromMeters(double meters) {
		String pretty;

		double feet = DistanceCalculator.metersToFeet(meters);

		if (feet > 1000) {
			DecimalFormat df = new DecimalFormat("####0.00");
			pretty = df.format(feet * 0.000189394) + " mi";
		} else {
			DecimalFormat df = new DecimalFormat("###0");
			pretty = df.format(feet) + " ft";
		}
		return pretty;
	}
}
