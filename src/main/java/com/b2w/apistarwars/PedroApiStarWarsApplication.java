package com.b2w.apistarwars;



import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import com.b2w.apistarwars.client.SWAPIRestTeamplate;
import com.b2w.apistarwars.models.Planeta;
import com.b2w.apistarwars.repository.PlanetaRepository;

@SpringBootApplication
public class PedroApiStarWarsApplication implements CommandLineRunner {

	@Autowired
	private PlanetaRepository planetrepo;
	
	@Autowired
	private SWAPIRestTeamplate swapi;

	protected static final Logger LOGGER = LoggerFactory.getLogger(PedroApiStarWarsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PedroApiStarWarsApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {

		swapi.TestaSWAPI();
		
		if(planetrepo.count() == 0) {
			planetrepo.save(new Planeta("Teste","Teste","Teste", "Teste"));
		}
		
		LOGGER.info("Encontrando planetas ou gerando uma galáxia muito, muito distante...");
		LOGGER.info("-------------------------------");
		
		Optional<Planeta> obj = planetrepo.findById("Teste");
		if(obj.isPresent()) {
			planetrepo.deleteById(obj.get().getId());
		}

	}
}
