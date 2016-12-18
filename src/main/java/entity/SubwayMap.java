package entity;


import java.util.List;

public class SubwayMap {
	
	private final List<Station> vertexes;
	private final List<Edge> edges;

	public SubwayMap(List<Station> vertexes, List<Edge> edges) {
		this.vertexes = vertexes;
		this.edges = edges;
	}

	public List<Station> getVertexes() {
		return vertexes;
	}

	public List<Edge> getEdges() {
		return edges;
	}

}