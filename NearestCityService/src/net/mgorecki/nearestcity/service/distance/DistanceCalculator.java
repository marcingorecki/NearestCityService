package net.mgorecki.nearestcity.service.distance;

import net.mgorecki.nearestcity.GeoPoint;

public class DistanceCalculator {

	public double getDistance(double latitude, double longitude, GeoPoint point) {

		double latDistance = latitude - point.getLatitude();
		double lonDistance = longitude - point.getLongitude();
		double distance = (double) Math.sqrt(latDistance * latDistance + lonDistance * lonDistance);

		return distance;
	}
}
