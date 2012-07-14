package net.mgorecki.nearestcity.service;

import net.mgorecki.nearestcity.GeoPoint;
import net.mgorecki.nearestcity.datasource.DataSource;
import net.mgorecki.nearestcity.service.distance.DistanceCalculator;

public class GetNearestService {
	
	public GeoPoint getNearest(double latitude, double longitude) {
		
		GeoPoint nearest = null;	
		double nearestDistance = Double.MAX_VALUE;
		DataSource ds = DataSource.getInstance();
		
		DistanceCalculator dc = new DistanceCalculator();
		
		for(GeoPoint point: ds.getData()){
			double pointDistance = dc.getDistance(latitude, longitude, point);
			if(pointDistance < nearestDistance) {
				nearestDistance = pointDistance;
				nearest = point;
			}
		}
		
		return nearest;
	}

}
