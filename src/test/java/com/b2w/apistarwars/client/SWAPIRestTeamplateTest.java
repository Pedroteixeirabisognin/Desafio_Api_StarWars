package com.b2w.apistarwars.client;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import com.b2w.apistarwars.models.ResultApiSW;

public class SWAPIRestTeamplateTest {
    
    @Autowired
    SWAPIRestTeamplate rest;

    @Before
	public void setUp() {
		rest = new SWAPIRestTeamplate();
	}
    
	@Test
    public void testa_Retorna_Aparicoes() {
		ResponseEntity<ResultApiSW> result = rest.RetornaAparicoes();
    	Assert.assertNotNull(result);
     }
}
 