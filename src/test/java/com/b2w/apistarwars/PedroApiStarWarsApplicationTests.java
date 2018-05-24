package com.b2w.apistarwars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.b2w.apistarwars.models.Planeta;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PedroApiStarWarsApplicationTests {

	
	protected final String BASE_PATH = "http://localhost:";
	protected static final Logger LOGGER = LoggerFactory.getLogger(PedroApiStarWarsApplicationTests.class);

	@LocalServerPort
	private int port;
	
	RestTemplate rest;
	
	@Before
	public void setUp() {
		rest = new RestTemplate();
	}
	
	@Test
	public void testaInsercaoPlaneta() {
		
		Planeta planeta = new Planeta("Teste","Teste","Teste","Teste");
		ResponseEntity<String> response = null;
		response = rest.postForEntity(BASE_PATH + port +"/planetas/",planeta,String.class);
		

		Assert.assertEquals(201, response.getStatusCodeValue());
		rest.delete(BASE_PATH + port +"/planetas/"+ "Teste");

	}
	@Test
	public void testaInsercaoComMesmoID() {
		
		Planeta planeta = new Planeta("Teste","Teste","Teste", "Teste");
		
		try {

			ResponseEntity<String> response = rest.postForEntity(BASE_PATH + port +"/planetas/",planeta,String.class);
			response = rest.postForEntity(BASE_PATH + port +"/planetas/",planeta,String.class);
			LOGGER.info("Não deveria receber " +  response.getStatusCodeValue());
			Assert.fail();
		
		}catch (Exception e) {
			
			Assert.assertEquals("400 null", e.getMessage() );
 		}
		rest.delete(BASE_PATH + port +"/planetas/"+ "Teste");

	}
	
	@Test
	public void testaBuscaID() {
		
		Planeta planeta = new Planeta("Teste","Teste","Teste", "Teste");
		ResponseEntity<String>  response = rest.postForEntity(BASE_PATH + port +"/planetas/",planeta,String.class);

		response = rest.getForEntity(BASE_PATH + port +"/planetas/buscaid?id=Teste", String.class);
		Assert.assertEquals(200, response.getStatusCodeValue());
		
		rest.delete(BASE_PATH + port +"/planetas/"+ "Teste");

	}
	
	@Test
	public void testaBuscaIDSeNaoExiste() {
		
		try {

			ResponseEntity<String> response = rest.getForEntity(BASE_PATH + port +"/planetas/buscaid?id=Teste", String.class);
			LOGGER.info("Não deveria receber " +  response.getStatusCodeValue());
		}catch(Exception e) {
			
			Assert.assertEquals("404 null", e.getMessage());
		}

	}
	
	@Test
	public void testaBuscaNome() {
		
		Planeta planeta = new Planeta("Teste","Teste","Teste", "Teste");
		ResponseEntity<String>  response = rest.postForEntity(BASE_PATH + port +"/planetas/",planeta,String.class);

		response = rest.getForEntity(BASE_PATH + port +"/planetas/buscanome?nome=Teste", String.class);
		Assert.assertEquals(200, response.getStatusCodeValue());
		
		rest.delete(BASE_PATH + port +"/planetas/"+ "Teste");

	}
	
	@Test
	public void testaBuscaNomeSeNaoExiste() {

			ResponseEntity<String> response = rest.getForEntity(BASE_PATH + port +"/planetas/buscanome?nome=Teste", String.class);
			Assert.assertEquals(200, response.getStatusCodeValue());

	}
	
	@Test
	public void testaBuscaTodos() {
		
		Planeta planeta1 = new Planeta("Teste1","Teste","Teste", "Teste");
		Planeta planeta2 = new Planeta("Teste2","Teste","Teste", "Teste");
		Planeta planeta3 = new Planeta("Teste3","Teste","Teste", "Teste");
		Planeta planeta4 = new Planeta("Teste4","Teste","Teste", "Teste");
		List<Planeta>  planetas = new ArrayList<Planeta>();
		planetas.add(planeta1);
		planetas.add(planeta2);
		planetas.add(planeta3);
		planetas.add(planeta4);
		ResponseEntity<String> response;
		
		response = rest.getForEntity(BASE_PATH + port +"/planetas/", String.class);
		Assert.assertEquals(200, response.getStatusCodeValue());
		

	}
	/*@LocalServerPort
	private int port;

	final String BASE_PATH = "http://localhost:";
		
	private TestRestTemplate restTemplate;
	
	@Before
    public void setUp(){
     
        restTemplate = new TestRestTemplate();
    }
	

	@Test
	public void testaInsercaoPlaneta() {
		
		restTemplate.delete(BASE_PATH + port +"/planetas/" + "teste");
		Planeta planetaTeste = new Planeta("teste","teste","teste","teste");
		ResponseEntity<Planeta> response = restTemplate.postForEntity(BASE_PATH + port +"/planetas/", planetaTeste, Planeta.class);
			
		Assert.assertEquals(201, response.getStatusCodeValue());
		restTemplate.delete(BASE_PATH + port +"/planetas/" + "teste");

		
	}
	
	@Test
	public void testaListarPlaneta() {
		
		Planeta planetaTeste = new Planeta("teste","teste","teste","teste");
		restTemplate.postForEntity(BASE_PATH + port +"/planetas/", planetaTeste, Planeta.class);
		ResponseEntity<String> responseRetorno = restTemplate.getForEntity(BASE_PATH + port +"/planetas/" + "buscaid?id=teste" , String.class);
		
		Assert.assertEquals(200, responseRetorno.getStatusCodeValue());
		 
		
	}
	
	@Test
	public void testaDeletarPlaneta() {
		
		restTemplate.delete(BASE_PATH + port +"/planetas/" + "teste");
		Planeta planetaTeste = new Planeta("teste","teste","teste","teste");
		restTemplate.postForEntity(BASE_PATH + port +"/planetas/", planetaTeste, Planeta.class);
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
	    ResponseEntity<String> responseRetorno = restTemplate.exchange(BASE_PATH + port +"/planetas/" + "teste",HttpMethod.DELETE, entity, String.class);

		Assert.assertEquals(204, responseRetorno.getStatusCodeValue());
		
		
	}
	
	@Test
	public void testaListarTodosOsPlanetasComItens() {
		
		
		Planeta planetaTeste1 = new Planeta("teste2","teste","teste","teste");
		Planeta planetaTeste2 = new Planeta("teste2","teste","teste","teste");
		Planeta planetaTeste3 = new Planeta("teste3","teste","teste","teste");
		List<Planeta> planetas = new ArrayList<Planeta>();
		planetas.add(planetaTeste1);
		planetas.add(planetaTeste2);
		planetas.add(planetaTeste3);
		restTemplate.postForEntity(BASE_PATH + port +"/planetas/", planetas, Planeta.class);
		ResponseEntity<String> responseRetorno = restTemplate.getForEntity(BASE_PATH + port +"/planetas/", String.class);
		
		Assert.assertEquals(200, responseRetorno.getStatusCodeValue());
		 
		
	}*/
}
