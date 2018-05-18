package com.b2w.apistarwars.client;

import org.springframework.web.client.RestTemplate;

import com.b2w.apistarwars.models.PlanetsApiExt;

public class SWAPIRestTeamplate {

	static RestTemplate restTemplate = new RestTemplate();
	public static PlanetsApiExt dadoApiExt() {
		PlanetsApiExt planetaSWAPI = restTemplate.getForObject("https://swapi.co/api/planets/", PlanetsApiExt.class);; 
		return planetaSWAPI;
	}
}
