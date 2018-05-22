package com.b2w.apistarwars;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.b2w.apistarwars.models.Planeta;
import com.b2w.apistarwars.repository.PlanetaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PedroApiStarWarsApplicationTests {
	
	final String BASE_PATH = "http://localhost:8080/planetas/";
	
	
	private TestRestTemplate restTemplate;
	
	@Mock
	private PlanetaRepository repository;

	private ObjectMapper MAPPER = new ObjectMapper();
	
	@Before
    public void setUp() throws Exception {
     
        //Inicializamos o objeto restTemplate
        restTemplate = new TestRestTemplate();
    }
	
	@Test
	public void testaInsercaoPlaneta() {
		
		Planeta planeta = new Planeta("123","Calbaran","Quente","Chuvoso");
		
		ResponseEntity<Planeta> response = restTemplate.postForEntity(BASE_PATH, planeta, Planeta.class);
		
		Assert.assertEquals(201, response.getStatusCodeValue());
		
		
	}
	
	@Test
	public void testaListarPlaneta() {
		
		ResponseEntity<String> response = restTemplate.getForEntity(BASE_PATH + "buscaid?id=123" , String.class);
		
		Assert.assertEquals(200, response.getStatusCodeValue());
		
		
	}
	
	@Test
	public void testaDeletarPlaneta() {

		restTemplate.delete(BASE_PATH + "123");
		ResponseEntity<String> response = restTemplate.getForEntity(BASE_PATH + "buscaid?id=123" , String.class);

		Assert.assertEquals(404, response.getStatusCodeValue());
		
		
	}
}
