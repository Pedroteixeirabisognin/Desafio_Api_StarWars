package com.b2w.apistarwars.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultApiSW {

	private List<PlanetaApiSW> results;

	public ResultApiSW(List<PlanetaApiSW> results, String name) {
		this.results = results;
	}

	
}
