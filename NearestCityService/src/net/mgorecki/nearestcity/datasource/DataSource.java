package net.mgorecki.nearestcity.datasource;

import java.util.List;

import net.mgorecki.nearestcity.GeoPoint;

public class DataSource {

	static List<GeoPoint> points = null;
	static DataSource dataSource = null;

	private DataSource() {
		DataSourceProvider reader = new USCensusTranslatedCSVFileReader();
		points = reader.read();
	};

	public static DataSource getInstance() {
		if (dataSource == null) {
			dataSource = new DataSource();
		}

		return dataSource;
	}

	public List<GeoPoint> getData() {

		return points;
	}
}
