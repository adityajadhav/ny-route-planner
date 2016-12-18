package entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Edge {

	private String id;
	private Station source;
	private Station destination;
	private long weight = Long.MAX_VALUE;
	private String line;

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public Edge() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Station getSource() {
		return source;
	}

	public void setSource(Station source) {
		this.source = source;
	}

	public Station getDestination() {
		return destination;
	}

	public void setDestination(Station destination) {
		this.destination = destination;
	}

	public long getWeight() {
		return weight;
	}

	@JsonProperty("duration")
	public void setWeight(long weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return source + " " + destination;
	}

}