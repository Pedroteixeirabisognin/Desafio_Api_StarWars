package com.b2w.apistarwars.services;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.b2w.apistarwars.models.Planeta;
import com.b2w.apistarwars.repository.PlanetaRepository;

public class PlanetaServiceTest {

	
	private PlanetaService serv;
	
	@Mock
	private PlanetaRepository repo;
	
	@Before
	public void setUp() {
		repo = Mockito.mock(PlanetaRepository.class); 
		serv = new PlanetaService(repo);

	}
	
	@Test
	public void testa_Insere() {
		Planeta planeta = new Planeta("Bluf","frozen","tundra");
		planeta.setId("Bluf");
		when(repo.save(planeta)).thenReturn(planeta);
		
		Planeta planetaRetorno = serv.insere(planeta);
		Assert.assertEquals(planetaRetorno.getNome(), planeta.getNome());
	}
	
	@Test
	public void testa_Listar_Todos() {
		Planeta planeta1 = new Planeta("Teste1","Teste", "Teste");
		Planeta planeta2 = new Planeta("Teste2","Teste", "Teste");
		Planeta planeta3 = new Planeta("Teste3","Teste", "Teste");
		Planeta planeta4 = new Planeta("Teste4","Teste", "Teste");
		List<Planeta>  planetas = new ArrayList<Planeta>();
		planetas.add(planeta1);
		planetas.add(planeta2);
		planetas.add(planeta3);
		planetas.add(planeta4);
		
		when(repo.findAll()).thenReturn(planetas);
		
		List<Planeta> planetasRetorno = serv.encontraTodos();
		Assert.assertEquals(planetasRetorno.get(0).getNome(), planeta1.getNome());
	}
	
	@Test
	public void testa_Encontra_Por_ID() {
		Planeta planeta1 = new Planeta("TesteNovo","Teste", "Teste");
		Optional<Planeta> planetaOpt = Optional.of(planeta1);
		planeta1.setId("Teste");
		when(repo.findById(planeta1.getId())).thenReturn(planetaOpt);
		
		Planeta planetasRetorno = serv.encontraPorId(planeta1.getId());
		Assert.assertEquals(planetaOpt.get(), planetasRetorno);
	}
	
	@Test
	public void testa_Encontra_Por_ID_Nao_Existente() {
		try {
			serv.encontraPorId("");
		}catch(Exception e) {
			Assert.assertEquals("Id não encontrada!!", e.getMessage() );
		}
	}
	
	@Test
	public void testa_Listar_Por_Nome() {
		Planeta planeta1 = new Planeta("Star Destroyer 1","Teste", "Teste");
		Planeta planeta2 = new Planeta("Star Destroyer 2","Teste", "Teste");
		Planeta planeta3 = new Planeta("Star Destroyer 3","Teste", "Teste");
		Planeta planeta4 = new Planeta("Star Destroyer 4","Teste", "Teste");
		List<Planeta>  planetas = new ArrayList<Planeta>();
		planetas.add(planeta1);
		planetas.add(planeta2);
		planetas.add(planeta3);
		planetas.add(planeta4);
		
		when(repo.findByNomeContaining("Teste")).thenReturn(planetas);
		 
		List<Planeta> planetasRetorno = serv.findByNome("Teste");
		Assert.assertEquals(planetasRetorno.get(0).getNome(), planeta1.getNome());
	}
}
