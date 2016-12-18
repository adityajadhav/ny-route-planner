package com.cs430.ajadhav4;

import org.junit.Test;

import com.cs430.ajadhav4.nyrouteplanner.entity.ShortestPath;

public class NewYorkSubwayTest {

	@Test
	public void shortestPathTest1() {

		ShortestPath shortestPath = new ShortestPath();
		/*
		 * { "id" : "613", "name" : "Hunts Point Av" }
		 * 
		 * { "id" : "618", "name" : "Brook Av" }
		 */

		shortestPath.calculateResult("F09", "M22");

	}

	@Test
	public void shortestPathTest2() {

		ShortestPath shortestPath = new ShortestPath();
		/*
		 * { "id" : "608", "name" : "Parkchester" },
		 */
		/*
		 * { "id" : "619", "name" : "3 Av - 138 St" },
		 */
		shortestPath.calculateResult("608", "619");

	}

}
