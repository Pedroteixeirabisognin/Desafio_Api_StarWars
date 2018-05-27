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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
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
	public void testa_Insercao_Planeta() {
		
		Planeta planeta = new Planeta("Luke","Teste","Teste");
		ResponseEntity<String> response = rest.postForEntity(BASE_PATH + port +"/planetas/",planeta,String.class);
		Assert.assertEquals(201, response.getStatusCodeValue());
		rest.delete(response.getHeaders().getLocation());

	}
		
	@Test
	public void testa_Busca_ID() {
		
		Planeta planeta = new Planeta("Léia","Teste", "Teste");
		ResponseEntity<String>  response = rest.postForEntity(BASE_PATH + port +"/planetas/",planeta,String.class);

		ResponseEntity<String>  respostaBusca = rest.getForEntity(response.getHeaders().getLocation(), String.class);
		Assert.assertEquals(200, respostaBusca.getStatusCodeValue());
		
		rest.delete(response.getHeaders().getLocation());

	}
	
	@Test
	public void testa_Busca_ID_Se_Nao_Existe() {
		
		try {

			rest.getForEntity(BASE_PATH + port +"/planetas/buscaid?id=Teste", String.class);
		}catch(Exception e) {
			
			Assert.assertEquals("404 null", e.getMessage());
		}

	}
	
	@Test
	public void testa_Busca_Nome() {
		
		Planeta planeta = new Planeta("Anakin","Teste","Teste", "Teste");
		ResponseEntity<String>  response = rest.postForEntity(BASE_PATH + port +"/planetas/",planeta,String.class);

		ResponseEntity<String>  respostaBusca = rest.getForEntity(BASE_PATH + port +"/planetas/buscanome?nome=Anakin", String.class);
		Assert.assertEquals(200, respostaBusca.getStatusCodeValue());
		
		rest.delete(response.getHeaders().getLocation());

	}
	
	@Test
	public void testa_Busca_Nome_Se_Nao_Existe() {

			ResponseEntity<String> response = rest.getForEntity(BASE_PATH + port +"/planetas/buscanome?nome=Teste", String.class);
			Assert.assertEquals(200, response.getStatusCodeValue());

	}
	
	@Test
	public void testa_Busca_Todos() {
		
		Planeta planeta1 = new Planeta("Teste1","Teste","Teste", "Teste");
		Planeta planeta2 = new Planeta("Teste2","Teste","Teste", "Teste");
		Planeta planeta3 = new Planeta("Teste3","Teste","Teste", "Teste");
		Planeta planeta4 = new Planeta("Teste4","Teste","Teste", "Teste");
		List<Planeta>  planetas = new ArrayList<Planeta>();
		planetas.add(planeta1);
		planetas.add(planeta2);
		planetas.add(planeta3);
		planetas.add(planeta4);
		ResponseEntity<String> response = rest.getForEntity(BASE_PATH + port +"/planetas/", String.class);
		Assert.assertEquals(200, response.getStatusCodeValue());
		

	}
	
	@Test
	public void testa_Deletar() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		Planeta planeta = new Planeta("Obi-Wan","Teste", "Teste");
		ResponseEntity<String>  response = rest.postForEntity(BASE_PATH + port +"/planetas/",planeta,String.class);

		ResponseEntity<String> respostaBusca  = rest.exchange(response.getHeaders().getLocation().toString(), HttpMethod.DELETE, entity , String.class,planeta);
		Assert.assertEquals(204, respostaBusca.getStatusCodeValue());

	}

	@Test
	public void testa_Deletar_Sem_Existir_Id() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        try {
			rest.exchange(BASE_PATH + port +"/planetas/"+ "Teste", HttpMethod.DELETE, entity , String.class);
        }catch(Exception e) {
			Assert.assertEquals("404 null", e.getMessage());
        }
	}
}
