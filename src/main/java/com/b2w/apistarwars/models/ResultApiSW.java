package com.b2w.apistarwars.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultApiSW {

	private List<PlanetaApiSW> results;

	
	public ResultApiSW() {
	}


	public ResultApiSW(List<PlanetaApiSW> results) {
		this.results = results;
	}


	public List<PlanetaApiSW> getResults() {
		return results;
	}

	public void setResults(List<PlanetaApiSW> results) {
		this.results = results;
	}
	
	
}
