package com.b2w.apistarwars.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.b2w.apistarwars.models.Planeta;

@RestController
@RequestMapping(value="/planetas")
public class PlanetaController {

	@GetMapping
	public ResponseEntity<List<Planeta>> findAll(){
		
		Planeta planeta = new Planeta("1", "String nome", "String clima", "String terreno", 2) ;
		List<Planeta> planetas = new ArrayList<>();
		planetas.add(planeta);
		return ResponseEntity.ok().body(planetas);
		
	}
}
