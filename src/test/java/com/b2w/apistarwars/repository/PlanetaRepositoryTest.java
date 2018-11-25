package com.b2w.apistarwars.repository;

import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.b2w.apistarwars.models.Planeta;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PlanetaRepositoryTest {

	Planeta planeta1, planeta2;
    
	@Autowired
    PlanetaRepository repository;
    
    @Before
    public void setUp() {
        
    	planeta1 = repository.save(new Planeta("Boba Fett", "teste","teste"));
        planeta2 = repository.save(new Planeta("Lando", "teste","teste"));
    }
    
    @After
    public void tearDown() {
    	
    	repository.delete(planeta1);
    	repository.delete(planeta2);
    }
    
    @Test
    public void testa_criar_planeta() {
        
    	Planeta planeta = repository.save(new Planeta("R2","teste","teste"));
        Assert.assertFalse(planeta.getId().isEmpty());
        repository.delete(planeta);
    }

    @Test
    public void testa_buscar_por_nome() {
    	
    	List<Planeta> result = repository.findByNomeContaining("Boba Fett");
    	Assert.assertFalse(result.isEmpty());
    }

    @Test
    public void testa_buscar_por_id() {
    	
    	Optional<Planeta> obj = repository.findById("Boba Fett");
    	Assert.assertNotNull(obj);
    }

    @Test
    public void testa_buscar_todos_planetas() {
      
    	List<Planeta> result = repository.findAll();
        
    	Assert.assertFalse(result.isEmpty());
    }
    
    @Test
    public void testa_deletar_planeta() {
    	
    	List<Planeta> planeta = repository.findByNomeContaining("Lando");
    	repository.delete(planeta.get(0));
    	List<Planeta> response = repository.findByNomeContaining("Lando");
    	Assert.assertTrue(response.isEmpty());

    	Assert.assertFalse(planeta.isEmpty());
    	 
    }
}
