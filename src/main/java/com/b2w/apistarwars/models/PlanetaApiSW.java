package com.b2w.apistarwars.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanetaApiSW {

	private List<String> films;

	
	
	public PlanetaApiSW() {

	}

	public PlanetaApiSW(List<String> films) {
		this.films = films;
	}

	public List<String> getFilms() {
		return films;
	}

	public void setFilms(List<String> films) {
		this.films = films;
	}
	
	
	
	
	
	
}
