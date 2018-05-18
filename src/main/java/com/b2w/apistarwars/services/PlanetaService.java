package com.b2w.apistarwars.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2w.apistarwars.client.SWAPIRestTeamplate;
import com.b2w.apistarwars.exception.ObjectNotFoundException;
import com.b2w.apistarwars.models.Planeta;
import com.b2w.apistarwars.models.PlanetsApiExt;
import com.b2w.apistarwars.repository.PlanetaRepository;

@Service
public class PlanetaService {

	@Autowired
	private PlanetaRepository repo;
	
	public Planeta insert(Planeta obj) {
		setIdNull(obj);
		return repo.save(obj);
	}
	public List<Planeta> findAll(){
		PlanetsApiExt planeta = SWAPIRestTeamplate.dadoApiExt();
		System.out.println(planeta.getFilms());
		
		return repo.findAll();
		
	}
	
	public Planeta findById(String id) {
		Optional<Planeta> obj = repo.findById(id);
		return  obj.orElseThrow(() -> new ObjectNotFoundException("Id não encontrada!!"));
	}
	
	public List<Planeta> findByNome(String text){
		return repo.findByNome(text);
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}
	
	public Planeta setIdNull(Planeta obj) {
		obj.setId(null);
		return obj;
	}
}
