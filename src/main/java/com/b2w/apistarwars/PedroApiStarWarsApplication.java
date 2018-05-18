package com.b2w.apistarwars;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.b2w.apistarwars.models.Planeta;
import com.b2w.apistarwars.repository.PlanetaRepository;

@SpringBootApplication
public class PedroApiStarWarsApplication implements CommandLineRunner {

	@Autowired
	private PlanetaRepository planetrepo;
	
	public static void main(String[] args) {
		SpringApplication.run(PedroApiStarWarsApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {

		if(planetrepo.count() == 0) {
			planetrepo.save(new Planeta("Teste","Teste","Teste", "Teste"));
		}
		
		System.out.println("Encontrando planetas ou gerando uma galáxia muito, muito distante...");
		System.out.println("-------------------------------");
		
		Optional<Planeta> obj = planetrepo.findById("Teste");
		if(obj.isPresent()) {
			planetrepo.deleteById(obj.get().getId());
		}
		
	}
}
