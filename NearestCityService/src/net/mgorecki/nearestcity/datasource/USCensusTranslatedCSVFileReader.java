package net.mgorecki.nearestcity.datasource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Date;
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
public class USCensusTranslatedCSVFileReader implements DataSourceProvider {

	private static final String CENSUS_FILENAME = "/uscities_census2010.txt";
	private Logger logger = Logger.getLogger(getClass().getCanonicalName());

	private List<GeoPoint> readCSVFile(InputStream csvStream) throws IOException {
		List<GeoPoint> list = new ArrayList<GeoPoint>();
		BufferedReader br = new BufferedReader(new InputStreamReader(csvStream), 8192);
		String line;
		boolean firstline = true;
		while ((line = br.readLine()) != null) {
			if (!firstline) {
				GeoPoint point = csvToPoint(line);
				list.add(point);
			} else {
				firstline = false;
			}
		}

		return list;
	}

	private List<GeoPoint> readCSVUsingStreamTokenizer(InputStream csvStream) throws IOException {
		List<GeoPoint> list = new ArrayList<GeoPoint>();
		Reader r = new BufferedReader(new InputStreamReader(csvStream));
		StreamTokenizer st = new StreamTokenizer(r);
		st.wordChars(32, 255);
		st.whitespaceChars('\t', '\t');
		st.eolIsSignificant(true);
		int field = 0;

		GeoPoint point = new GeoPoint();

		boolean firstline = true;
		while (st.nextToken() != StreamTokenizer.TT_EOF) {
			if (st.ttype == StreamTokenizer.TT_EOL) {

				if (!firstline) {
					list.add(point);
				} else {
					firstline = false;
				}
				point = new GeoPoint();
				continue;
			}
			field++;

			switch (field) {
			case 1:
				point.setState(st.sval);
				break;
			case 2:
				point.setName(st.sval);
				break;
			case 3:
				point.setLongitude((float) st.nval);
				break;
			case 4:
				point.setLatitude((float) st.nval);
				field = 0;
				break;
			}
		}

		return list;
	}

	private GeoPoint csvToPoint(String line) {
		GeoPoint point = new GeoPoint();

		String[] data = line.split("\t");

		point.setCountry("USA");
		point.setName(data[1]);
		point.setState(data[0]);
		point.setLatitude(Float.parseFloat(data[3]));
		point.setLongitude(Float.parseFloat(data[2]));

		return point;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.mgorecki.nearestcity.datasource.DataSourceProvider#read()
	 */
	@Override
	public List<GeoPoint> read() {
		Date d1 = new Date();
		List<GeoPoint> list = null;
		for (int i = 0; i < 1; i++) {
			InputStream csvStream = getClass().getResourceAsStream(CENSUS_FILENAME);

			try {
				// list = readCSVFile(csvStream);
				list = readCSVUsingStreamTokenizer(csvStream);
			} catch (IOException e) {
				logger.severe("Error reading " + CENSUS_FILENAME + " file: " + e.getLocalizedMessage());
				list = new ArrayList<GeoPoint>();
			}
		}
		Date d2 = new Date();
		logger.severe("execution time=" + (d2.getTime() - d1.getTime()));
		return list;
	}
}
