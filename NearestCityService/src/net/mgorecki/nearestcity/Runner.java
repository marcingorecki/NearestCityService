package net.mgorecki.nearestcity;

import net.mgorecki.nearestcity.datasource.DataSource;
import net.mgorecki.nearestcity.service.GetNearestService;
import net.mgorecki.nearestcity.service.distance.DistanceFormatter;

public class Runner {

	public static void main(String[] args) {

		GetNearestService gn = new GetNearestService();

		System.out.println(gn.getNearest(30.523716, -97.667363));
		System.out.println(gn.getNearest(30.534923, -97.670110));

		System.out.println(gn.getNearest(0, 0));
		System.out.println(gn.getNearest(35.823093, -101.443620));
		System.out.println(gn.getNearest(38.273798, -112.640143));

		DataSource sc = DataSource.getInstance();
		System.out.println("Total geopoints:" + sc.getData().size());

		System.out.println(DistanceFormatter.prettyMetricFromMeters(93424.234d));
		System.out.println(DistanceFormatter.prettyMetricFromMeters(1.654d));

		System.out.println(DistanceFormatter.prettyMilesFromMeters(93424.234d));
		System.out.println(DistanceFormatter.prettyMilesFromMeters(1.654d));
	}
}
