package net.mgorecki.nearestcity.service.distance;

import net.mgorecki.nearestcity.GeoPoint;

public class DistanceCalculator {

	public double getDistanceDegs(double latitude, double longitude, GeoPoint point) {

		double latDistance = latitude - point.getLatitude();
		double lonDistance = longitude - point.getLongitude();
		double distance = (double) Math.sqrt(latDistance * latDistance + lonDistance * lonDistance);

		return distance;
	}

	// using very simplified algorithm, do not try this on poles!
	// http://gis.stackexchange.com/questions/2951/algorithm-for-offsetting-a-latitude-longitude-by-some-amount-of-meters
	public double distanceToMeters(double distance) {
		return 111111 * distance;
	}

	public static double metersToFeet(double meters) {
		return meters * 3.2808;
	}

	public static double metersToMiles(double meters) {
		return meters * 0.000621371;
	}

}
