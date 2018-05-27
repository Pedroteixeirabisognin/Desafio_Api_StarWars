package com.b2w.apistarwars.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2w.apistarwars.exception.BadRequest;
import com.b2w.apistarwars.exception.ObjectNotFoundException;
import com.b2w.apistarwars.models.Planeta;
import com.b2w.apistarwars.repository.PlanetaRepository;

@Service
public class PlanetaService {

	@Autowired
	private PlanetaRepository repo;
	
	public Planeta insert(Planeta obj) {
		verificaConteudo(obj);
		geraId(obj);
		return repo.save(obj);
	}
	
	public List<Planeta> findAll(){
		return repo.findAll();
	}
	
	public Planeta findById(String id) {
		Optional<Planeta> obj = repo.findById(id);
		return  obj.orElseThrow(() -> new ObjectNotFoundException("Id não encontrada!!"));
	}
	
	public List<Planeta> findByNome(String nome){
		return repo.findByNomeContaining(nome);
	}
	
	public void delete(String id) {
		repo.delete(findById(id));
	}
	
	public Planeta geraId(Planeta obj) {
		Planeta id = repo.save(new Planeta());     
		obj.setId(id.getId());
		return obj;
	}
	
	public Planeta verificaConteudo(Planeta obj) {
		try {
			if(obj.getNome().isEmpty() || obj.getNome().equals(null)) {
				throw new BadRequest("Campo nome vazio");
			}
			if(obj.getClima().isEmpty()|| obj.getClima().equals(null)) {
				throw new BadRequest("Campo clima vazio");
			}
			if(obj.getTerreno().isEmpty()|| obj.getTerreno().equals(null)) {
				throw new BadRequest("Campo terreno vazio");
			}
		}catch (Exception e) {
			throw new BadRequest("Campo não pode receber nulo");
		}
		return obj;
		
	}
}
