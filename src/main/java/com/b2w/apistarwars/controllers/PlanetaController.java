package com.b2w.apistarwars.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.b2w.apistarwars.models.Planeta;
import com.b2w.apistarwars.services.PlanetaService;

@RestController
@RequestMapping(value="/planetas")
public class PlanetaController {

	@Autowired
	private PlanetaService service;
	
	@GetMapping
	public ResponseEntity<List<Planeta>> findAll(){
		List<Planeta> planetas = service.findAll();
		return ResponseEntity.ok().body(planetas);
		
	}
}
