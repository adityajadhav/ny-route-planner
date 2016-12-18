package com.cs430.ajadhav4.nyrouteplanner.entity;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import entity.Edge;
import entity.Station;
import entity.SubwayMap;

public class ShortestPath {

	SubwayMap graph = null;
	HashMap<String, Station> stationsMap;

	public void calculateResult(String sourceId, String destinationId) {

		loadGraph();
		Station source = stationsMap.get(sourceId);
		Station destination = stationsMap.get(destinationId);
		System.out.println("From " + source.getName() + " --> " + destination.getName());
		System.out.println();
		ShortestPathDijkstraAlgorithm dijkstra = new ShortestPathDijkstraAlgorithm(graph);
		dijkstra.execute(source);
		Long shortestPath = dijkstra.getShortestDistance(destination);

		String stopageDurationAtEachStation = PropertyReader.getInstance().getProperty("stop-duration");

		LinkedList<Station> path = dijkstra.getPath(destination);
		if (path != null && path.size() > 0) {
			int StopageTime = Integer.parseInt(stopageDurationAtEachStation) * path.size();
			System.out.println("Total wait time at staions " + StopageTime + " s");
			System.out.println();
			System.out.println("You will reach in " + (shortestPath + StopageTime) / 60 + " minutes");
			System.out.println();
		}
		System.out.println("You will pass through:");

		for (Station vertex : path) {
			System.out.println("\t" + vertex);
		}
		System.out.println("***************************************");
	}

	private void loadGraph() {

		stationsMap = new HashMap<String, Station>();
		ObjectMapper mapper = new ObjectMapper();
		List<Station> stationList = null;
		List<Edge> edgeList = null;
		URL stationURI = ShortestPath.class.getClassLoader().getResource("station.json");
		URL edgeURI = ShortestPath.class.getClassLoader().getResource("edge.json");

		try {
			stationList = mapper.readValue(stationURI, new TypeReference<List<Station>>() {
			});
			edgeList = mapper.readValue(edgeURI, new TypeReference<List<Edge>>() {
			});

		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (Station station : stationList) {
			stationsMap.put(station.getId(), station);
		}

		ArrayList<Edge> temp = new ArrayList<Edge>();
		for (Edge e : edgeList) {
			Edge manual = new Edge();
			manual.setSource(e.getDestination());
			manual.setDestination(e.getSource());
			manual.setWeight(e.getWeight());
			temp.add(manual);
		}

		for (Edge e : temp) {
			edgeList.add(e);
		}

		graph = new SubwayMap(stationList, edgeList);
	}

}
