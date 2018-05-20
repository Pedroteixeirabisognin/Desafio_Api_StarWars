package com.b2w.apistarwars.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.b2w.apistarwars.client.SWAPIRestTeamplate;
import com.b2w.apistarwars.models.Planeta;
import com.b2w.apistarwars.models.PlanetaResponse;
import com.b2w.apistarwars.services.PlanetaService;
import com.b2w.apistarwars.util.URL;

@RestController
@RequestMapping(value="/planetas")
public class PlanetaController {

	@Autowired
	private PlanetaService service;
	
	@Autowired
	private SWAPIRestTeamplate swapi;
	
	@PostMapping
	public ResponseEntity<Void> inserePlaneta(@RequestBody Planeta planeta){
		planeta = service.insert(planeta);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(planeta.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping
	public ResponseEntity<List<PlanetaResponse>> encontraTodos(){
		List<Planeta> planetas = service.findAll();
		List<PlanetaResponse> response = new ArrayList<>();
		planetas.forEach((x)->response.add(new PlanetaResponse(x.getNome(),x.getClima(),x.getTerreno(),swapi.RetornaAparicoes(x.getNome())))); 
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping(value="/buscaid")
	public ResponseEntity<PlanetaResponse> encontraPorID(@RequestParam(value="id", defaultValue="") String id){
		Planeta planeta = service.findById(id);
		PlanetaResponse resposta = new PlanetaResponse(planeta.getNome(),planeta.getClima(),planeta.getTerreno(),swapi.RetornaAparicoes(planeta.getNome()));
		return ResponseEntity.ok().body(resposta);
	}
	
	@GetMapping(value="/buscanome")
	public ResponseEntity<List<PlanetaResponse>> encontraPorNome(@RequestParam(value="nome", defaultValue="") String nome){
		
		nome = URL.decodeParam(nome);
		List<Planeta> planetas = service.findByNome(nome);
		List<PlanetaResponse> response = new ArrayList<>();
		planetas.forEach((x)->response.add(new PlanetaResponse(x.getNome(),x.getClima(),x.getTerreno(),swapi.RetornaAparicoes(x.getNome())))); 
		return ResponseEntity.ok().body(response);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deletaPlaneta(@PathVariable String id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	

}
