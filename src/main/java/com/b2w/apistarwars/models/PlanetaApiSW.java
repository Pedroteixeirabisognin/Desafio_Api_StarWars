package com.b2w.apistarwars.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanetaApiSW {

	private String name;

	private List<String> films;

		
	public PlanetaApiSW() {

	}

	public PlanetaApiSW(String name, List<String> films) {
		this.name = name;
		this.films = films;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getFilms() {
		return films;
	}

	public void setFilms(List<String> films) {
		this.films = films;
	}
	
	
	
	
	
	
}
