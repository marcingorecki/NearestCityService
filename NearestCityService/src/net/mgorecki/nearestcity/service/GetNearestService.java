package net.mgorecki.nearestcity.service;

import net.mgorecki.nearestcity.GeoPoint;
import net.mgorecki.nearestcity.NearestGeoPoint;
import net.mgorecki.nearestcity.datasource.DataSource;
import net.mgorecki.nearestcity.service.distance.DistanceCalculator;

public class GetNearestService {

	public NearestGeoPoint getNearest(double latitude, double longitude) {

		NearestGeoPoint nearest = new NearestGeoPoint();
		GeoPoint nearestPoint = null;
		double nearestDistanceDeg = Double.MAX_VALUE;
		DataSource ds = DataSource.getInstance();

		DistanceCalculator dc = new DistanceCalculator();

		for (GeoPoint point : ds.getData()) {
			double pointDistance = dc.getDistanceDegs(latitude, longitude, point);
			if (pointDistance < nearestDistanceDeg) {
				nearestDistanceDeg = pointDistance;
				nearestPoint = point;
			}
		}

		double nearestDistanceMeters = dc.distanceToMeters(nearestDistanceDeg);

		nearest.setPoint(nearestPoint);
		nearest.setDistance(nearestDistanceMeters);

		return nearest;
	}
}
