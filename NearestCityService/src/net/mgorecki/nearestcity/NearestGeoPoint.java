package net.mgorecki.nearestcity;

public class NearestGeoPoint {
	private GeoPoint point;
	private double distance;

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public GeoPoint getPoint() {
		return point;
	}

	public void setPoint(GeoPoint point) {
		this.point = point;
	}

	public String toString() {
		return point + " is " + Math.round(distance) + "m away";
	}
}
