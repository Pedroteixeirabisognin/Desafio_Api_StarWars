package com.b2w.apistarwars.client;


import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.b2w.apistarwars.PedroApiStarWarsApplication;
import com.b2w.apistarwars.models.ResultApiSW;

@Service
public class SWAPIRestTeamplate {
	
    @Value("${apiSWAPI}")
    String url;
    
	protected static final Logger LOGGER = LoggerFactory.getLogger(PedroApiStarWarsApplication.class);

	public void TestaSWAPI() {
		
				
			RestTemplate restTemplate = new RestTemplate();
	        HttpHeaders headers = new HttpHeaders();
	        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
	
	        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
	
	        ResponseEntity<ResultApiSW> testaApi = restTemplate.exchange(url, HttpMethod.GET,entity,ResultApiSW.class);

	        if(testaApi != null) {
	        	LOGGER.info("SWAPI está online");
	        }
	        
	}
	public int RetornaAparicoes(String nomePlaneta) {
		
	    
    	try {
    		RestTemplate restTemplate = new RestTemplate();
	        HttpHeaders headers = new HttpHeaders();
	        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

	        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
    		ResponseEntity<ResultApiSW> response = restTemplate.exchange(url + "/?search=" + nomePlaneta , HttpMethod.GET,entity,ResultApiSW.class);
            return response.getBody().getResults().get(0).getFilms().size();
    	
    	}catch(Exception e) {
    		
    		
    		return 0;
    	}
	        
	
	}
		

}
	    	

