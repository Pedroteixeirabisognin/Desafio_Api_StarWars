package com.b2w.apistarwars;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	protected static final Logger LOGGER = LoggerFactory.getLogger(PedroApiStarWarsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PedroApiStarWarsApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		Planeta planeta;
		LOGGER.info("Encontrando planetas ou gerando uma galáxia muito, muito distante...");
		LOGGER.info("-------------------------------");
		if(planetrepo.count() == 0) {
			planeta = planetrepo.save(new Planeta());
			planetrepo.deleteById(planeta.getId());
		}
	
	}

}
