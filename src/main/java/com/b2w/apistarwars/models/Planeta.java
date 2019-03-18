package com.b2w.apistarwars.models;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
@NoArgsConstructor
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

	public Planeta(@NotBlank String nome, @NotBlank String clima, @NotBlank String terreno) {
		super();
		this.nome = nome;
		this.clima = clima;
		this.terreno = terreno;
	}
}
