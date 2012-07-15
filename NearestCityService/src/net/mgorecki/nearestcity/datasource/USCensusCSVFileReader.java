package net.mgorecki.nearestcity.datasource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import net.mgorecki.nearestcity.GeoPoint;

/**
 * CSV reader configured to use data from US census:
 * http://www.census.gov/geo/www/gazetteer/gazetteer2010.html
 * 
 * More generic solution will be added when another data type will be required.
 * 
 * @author marcin
 * 
 */
public class USCensusCSVFileReader {

	private static final String CENSUS_FILENAME = "/Gaz_places_national.csv";
	private Logger logger = Logger.getLogger(getClass().getCanonicalName());

	private List<GeoPoint> readCSVFile(InputStream csvStream) throws IOException {
		List<GeoPoint> list = new ArrayList<GeoPoint>();
		BufferedReader br = new BufferedReader(new InputStreamReader(csvStream), 8192);
		String line;
		boolean firstline = true;
		while ((line = br.readLine()) != null) {
			if (!firstline) {
				GeoPoint point = csvToPoint(line);
				// skip http://en.wikipedia.org/wiki/Census-designated_place
				if (!point.getName().endsWith("CDP")) {
					list.add(point);
				}
			} else {
				firstline = false;
			}
		}

		return list;
	}

	private GeoPoint csvToPoint(String line) {
		GeoPoint point = new GeoPoint();

		String[] data = line.split("\t");

		point.setCountry("USA");
		point.setName(data[3]);
		point.setState(data[0]);
		point.setLatitude(Float.parseFloat(data[8]));
		point.setLongitude(Float.parseFloat(data[9]));

		return point;
	}

	public List<GeoPoint> read() {
		InputStream csvStream = getClass().getResourceAsStream(CENSUS_FILENAME);
		List<GeoPoint> list;
		try {
			list = readCSVFile(csvStream);
		} catch (IOException e) {
			logger.severe("Error reading " + CENSUS_FILENAME + " file: " + e.getLocalizedMessage());
			list = new ArrayList<GeoPoint>();
		}
		return list;
	}

}
