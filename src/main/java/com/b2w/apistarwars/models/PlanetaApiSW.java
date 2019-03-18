package com.b2w.apistarwars.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlanetaApiSW {

	private String name;

	private List<String> films;

	public PlanetaApiSW(String name, List<String> films) {
		this.name = name;
		this.films = films;
	}

}
