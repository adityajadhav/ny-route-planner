package com.cs430.ajadhav4.nyrouteplanner.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import entity.Edge;
import entity.Station;
import entity.SubwayMap;

public class ShortestPathDijkstraAlgorithm {
	private List<Station> nodes;
	private List<Edge> edges;
	private Set<Station> settledNodes;
	private Set<Station> unSettledNodes;
	private Map<Station, Station> predecessors;
	private Map<Station, Long> distance;

	private void findMinimalDistances(Station node) {
		List<Station> adjacentNodes = getNeighbors(node);
		for (Station target : adjacentNodes) {
			if (getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)) {
				distance.put(target, getShortestDistance(node) + getDistance(node, target));
				predecessors.put(target, node);
				unSettledNodes.add(target);
			}
		}

	}

	public Long getShortestDistance(Station destination) {
		Long d = distance.get(destination);
		if (d == null) {
			return Long.MAX_VALUE;
		} else {
			return d;
		}
	}

	private long getDistance(Station node, Station target) {
		for (Edge edge : edges) {
			if (edge.getSource().equals(node) && edge.getDestination().equals(target)) {
				return edge.getWeight();
			}
		}
		throw new RuntimeException("");
	}

	private List<Station> getNeighbors(Station node) {
		List<Station> neighbors = new ArrayList<Station>();
		for (Edge edge : edges) {
			if (edge.getSource().equals(node) && !isSettled(edge.getDestination())) {
				neighbors.add(edge.getDestination());
			}
		}
		return neighbors;
	}

	private Station getMinimum(Set<Station> vertexes) {
		Station minimum = null;
		for (Station vertex : vertexes) {
			if (minimum == null) {
				minimum = vertex;
			} else {
				if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
					minimum = vertex;
				}
			}
		}
		return minimum;
	}

	private boolean isSettled(Station vertex) {
		return settledNodes.contains(vertex);
	}

	public LinkedList<Station> getPath(Station target) {
		LinkedList<Station> path = new LinkedList<Station>();
		Station step = target;
		if (predecessors.get(step) == null) {
			return null;
		}
		path.add(step);
		while (predecessors.get(step) != null) {
			step = predecessors.get(step);
			path.add(step);
		}
		Collections.reverse(path);
		return path;
	}

	public ShortestPathDijkstraAlgorithm(SubwayMap graph) {
		this.nodes = new ArrayList<Station>(graph.getVertexes());
		this.edges = new ArrayList<Edge>(graph.getEdges());
	}

	public void execute(Station source) {
		settledNodes = new HashSet<Station>();
		unSettledNodes = new HashSet<Station>();
		distance = new HashMap<Station, Long>();
		predecessors = new HashMap<Station, Station>();
		distance.put(source, 0L);
		unSettledNodes.add(source);
		while (unSettledNodes.size() > 0) {
			Station node = getMinimum(unSettledNodes);
			settledNodes.add(node);
			unSettledNodes.remove(node);
			findMinimalDistances(node);
		}
	}
}
