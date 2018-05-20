package com.b2w.apistarwars;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.b2w.apistarwars.models.Planeta;
import com.b2w.apistarwars.models.ResultApiSW;
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

				
		if(planetrepo.count() == 0) {
			planetrepo.save(new Planeta("Teste","Teste","Teste", "Teste"));
		}
		
		LOGGER.info("Encontrando planetas ou gerando uma galáxia muito, muito distante...");
		LOGGER.info("-------------------------------");
		
		Optional<Planeta> obj = planetrepo.findById("Teste");
		if(obj.isPresent()) {
			planetrepo.deleteById(obj.get().getId());
		}
		
		String url = "https://swapi.co/api/planets/";
		RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<ResultApiSW> response = restTemplate.exchange(url, HttpMethod.GET,entity,ResultApiSW.class);
        System.out.println(response);
        System.out.println(response.getBody().getResults().get(0).getFilms());
        List<String> a = new ArrayList<>();
        a = response.getBody().getResults().get(0).getFilms();
        System.out.println(a.size());

	
    
	}
}
