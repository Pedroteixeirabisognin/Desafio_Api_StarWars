package com.b2w.apistarwars.models;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;



@Document
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({ "id", "nome", "clima", "terreno" })
public class Planeta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	@NotBlank
	@JsonProperty(required = true)
	private String nome;
	@NotBlank
	@JsonProperty(required = true)
	private String clima;
	@NotBlank
	@JsonProperty(required = true)
	private String terreno;

	
	public Planeta() {
		super();
	}
	public Planeta(String nome, String clima, String terreno) {
		super();
		this.nome = nome;
		this.clima = clima;
		this.terreno = terreno;

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


	
	
}
