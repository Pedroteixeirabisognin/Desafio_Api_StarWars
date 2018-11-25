package com.b2w.apistarwars.controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.b2w.apistarwars.models.PlanetaApiSW;
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
	
	private Calendar horaInicial = Calendar.getInstance(); 

	private List<PlanetaApiSW> result = new ArrayList<PlanetaApiSW>();  

	@PostMapping
	public ResponseEntity<Void> inserePlaneta(@RequestBody Planeta planeta){
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(service.insere(planeta).getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping
	public ResponseEntity<List<PlanetaResponse>> encontraTodos(){
		return ResponseEntity.ok().body(insereAparicao(service.encontraTodos()));
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<PlanetaResponse> encontraPorID(@PathVariable("id") String id){
		Planeta planeta = service.encontraPorId(id);
		this.result =  implementaCache(this.result, horaInicial);
		return ResponseEntity.ok().body(new PlanetaResponse(planeta.getId(),planeta.getNome(),planeta.getClima(),planeta.getTerreno(),encontraAparicao(result, planeta)));
	}
	
	@GetMapping(value="/buscanome")
	public ResponseEntity<List<PlanetaResponse>> encontraPorNome(@RequestParam(value="nome", defaultValue="") String nome){
		List<PlanetaResponse> resposta = new ArrayList<>();
		this.result =  implementaCache(this.result, horaInicial);
		for(Planeta x: service.findByNome(URL.decodeParam(nome)) ) {
			
			resposta.add(new PlanetaResponse(x.getId(),x.getNome(),x.getClima(),x.getTerreno(),encontraAparicao(result,x)));
		}
		return ResponseEntity.ok().body(resposta);
	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Void> deletaPlaneta(@PathVariable String id){
		service.deleta(id);
		return ResponseEntity.noContent().build();
	}

	public List<PlanetaResponse> insereAparicao(List<Planeta> planetas) {
		List<PlanetaResponse> resposta = new ArrayList<>();
		this.result =  implementaCache(this.result, horaInicial);
		for(Planeta x: planetas ) {
		
			resposta.add(new PlanetaResponse(x.getId(),x.getNome(),x.getClima(),x.getTerreno(),encontraAparicao(result,x)));
		}
		return resposta;
	}
	private int encontraAparicao(List<PlanetaApiSW> result,Planeta planeta) {
		for(PlanetaApiSW y: result ) {
			if(planeta.getNome().equals(y.getName())) {
				return y.getFilms().size();
			}
		}	
		return 0;
	}
	 
	//A CADA UMA HORA BAIXA NOVAMENTE A LISTA DA API POIS A API TEM UM LIMITE DE SOLICITAÇÕES POR DIA
	private List<PlanetaApiSW> implementaCache(List<PlanetaApiSW> result, Calendar horaInicial) {
		Calendar atual = Calendar.getInstance(); 
		Calendar horaComparar = (Calendar) horaInicial.clone();
		horaComparar.add(Calendar.HOUR_OF_DAY, 1);
		if(result.isEmpty()) {
			result = swapi.RetornaAparicoes().getBody().getResults(); 
		}
		if(atual.after(horaComparar)) {
			result = swapi.RetornaAparicoes().getBody().getResults(); 
			horaInicial = Calendar.getInstance(); 
		}
		return result;
	}
}
