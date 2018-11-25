package com.b2w.apistarwars.models;


public class PlanetaResponse {

	private String id;
	private String nome;
	private String clima;
	private String terreno;
	private int aparicoes;
	
	public PlanetaResponse() {
	}

	
	public PlanetaResponse(String id, String nome, String clima, String terreno, int aparicoes) {
		this.id = id;
		this.nome = nome;
		this.clima = clima;
		this.terreno = terreno;
		this.aparicoes = aparicoes;
	}
	public String getId() {
		return id;
	} 
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getClima() {
		return clima;
	}
	public void setClima(String clima) {
		this.clima = clima;
	}
	public String getTerreno() {
		return terreno;
	}
	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}
	public int getAparicoes() {
		return aparicoes;
	}
	public void setAparicoes(int aparicoes) {
		this.aparicoes = aparicoes;
	}



		
		

}
