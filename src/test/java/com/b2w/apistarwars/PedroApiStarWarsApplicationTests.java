package com.b2w.apistarwars;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.b2w.apistarwars.models.Planeta;
import com.b2w.apistarwars.repository.PlanetaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PedroApiStarWarsApplicationTests {
	
	@LocalServerPort
	private int port;

	final String BASE_PATH = "http://localhost:";
		
	private TestRestTemplate restTemplate;
	
	private ObjectMapper MAPPER = new ObjectMapper();
	
	@Before
    public void setUp(){
     
        restTemplate = new TestRestTemplate();
    }
	
	@Test
	public void testaInsercaoPlaneta() {
		
		Planeta planetaTeste = new Planeta("123","Calbaran","Quente","Chuvoso");
		ResponseEntity<Planeta> response = restTemplate.postForEntity(BASE_PATH + port +"/planetas/", planetaTeste, Planeta.class);
			
		Assert.assertEquals(201, response.getStatusCodeValue());
		
		
	}
	
	@Test
	public void testaListarPlaneta() {
		
		ResponseEntity<String> response = restTemplate.getForEntity(BASE_PATH + port +"/planetas/" + "buscaid?id=123" , String.class);
		
		Assert.assertEquals(200, response.getStatusCodeValue());
		
		
	}
	
	@Test
	public void testaDeletarPlaneta() {

		restTemplate.delete(BASE_PATH + port +"/planetas/" + "123");
		ResponseEntity<String> response = restTemplate.getForEntity(BASE_PATH + port +"/planetas/" + "123" , String.class);

		Assert.assertEquals(201, response.getStatusCodeValue());
		
		
	}
}
