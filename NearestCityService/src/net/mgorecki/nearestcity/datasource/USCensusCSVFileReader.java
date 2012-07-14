package net.mgorecki.nearestcity.datasource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

	private List<File> findAllCSVFiles() {
		File dataDir = new File("data");
		File[] csvFiles = dataDir.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".csv");
			}
		});

		return Arrays.asList(csvFiles);
	}

	private List<GeoPoint> readCSVFile(File csvFile) throws IOException {
		List<GeoPoint> list = new ArrayList<GeoPoint>();
		BufferedReader br = new BufferedReader(new FileReader(csvFile));
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
		List<GeoPoint> list = new ArrayList<GeoPoint>();

		for (File csvFile : findAllCSVFiles()) {
			try {
				list.addAll(readCSVFile(csvFile));
			} catch (IOException e) {
				// broken file, skip it
			}
		}

		return list;
	}

}
