package net.mgorecki.nearestcity.datasource;

import java.util.List;

import net.mgorecki.nearestcity.GeoPoint;

public interface DataSourceProvider {

	public abstract List<GeoPoint> read();

}