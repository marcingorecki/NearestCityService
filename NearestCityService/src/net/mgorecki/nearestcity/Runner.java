package net.mgorecki.nearestcity;

import net.mgorecki.nearestcity.service.GetNearestService;

public class Runner {

	public static void main(String[] args) {

		GetNearestService gn = new GetNearestService();

		System.out.println(gn.getNearest(30.523716, -97.667363));
		System.out.println(gn.getNearest(0, 0));
		System.out.println(gn.getNearest(35.823093, -101.443620));
		System.out.println(gn.getNearest(38.273798, -112.640143));
	}
}
