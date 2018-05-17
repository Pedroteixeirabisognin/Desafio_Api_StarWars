package com.b2w.apistarwars.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanetsApiExt {

	private String filmes;

	public PlanetsApiExt() {
	}
	
	public PlanetsApiExt(String filmes) {
		super();
		this.filmes = filmes;
	}

	public String getFilmes() {
		return filmes;
	}

	public void setFilmes(String filmes) {
		this.filmes = filmes;
	}
	
	
}
