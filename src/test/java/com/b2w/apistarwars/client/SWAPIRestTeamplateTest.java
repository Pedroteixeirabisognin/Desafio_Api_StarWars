package com.b2w.apistarwars.client;

import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;

import com.b2w.apistarwars.models.ResultApiSW;



public class SWAPIRestTeamplateTest {

    @Value("${apiSWAPI}")
    String url;
    
    @Autowired
    SWAPIRestTeamplate rest;
	@Before
	public void setUp() {
		rest = Mockito.mock(SWAPIRestTeamplate.class);
	}
    
	@Test
    public void testa_Retorna_Aparicoes_Quando_Retorna_Null() {
 		when(rest.RetornaAparicoes()).thenThrow(new RestClientException("404 null"));
 		ResponseEntity<ResultApiSW> result = null;
 		try {
			result = rest.RetornaAparicoes();
		}catch (Exception e) {
			Assert.assertNull(result);
		}
     }
	
	@Test
    public void testa_Retorna_Aparicoes() {
		ResultApiSW ResultAPI = new ResultApiSW();
		when(rest.RetornaAparicoes()).thenReturn(new ResponseEntity<ResultApiSW>(ResultAPI, HttpStatus.OK ));
		ResponseEntity<ResultApiSW> result = rest.RetornaAparicoes();
    	Assert.assertNotNull(result);
     }
}
