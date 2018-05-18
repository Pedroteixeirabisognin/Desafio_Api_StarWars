package com.b2w.apistarwars.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanetsApiExt {

	private String films;

	public PlanetsApiExt() {
	}
	
	public PlanetsApiExt(String films) {
		this.films = films;
	}

	public String getFilms() {
		return films;
	}

	public void setFilms(String filmes) {
		this.films = filmes;
	}
	
	
}
