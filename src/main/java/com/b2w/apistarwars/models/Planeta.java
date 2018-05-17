package com.b2w.apistarwars.models;

import java.io.Serializable;

public class Planeta implements Serializable {
	private static final long serialVersionUID = 1L;

	private String nome;
	private String clima;
	private String terreno;
	private int aparições;
	
	public Planeta() {
		super();
	}
	
	public Planeta(String nome, String clima, String terreno, int aparições) {
		super();
		this.nome = nome;
		this.clima = clima;
		this.terreno = terreno;
		this.aparições = aparições;
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

	public int getAparições() {
		return aparições;
	}

	public void setAparições(int aparições) {
		this.aparições = aparições;
	}
	
	
}
