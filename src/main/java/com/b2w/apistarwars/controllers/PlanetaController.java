package com.b2w.apistarwars.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.b2w.apistarwars.models.Planeta;

@RestController
@RequestMapping(value="/planetas")
public class PlanetaController {

	public List<Planeta> findAll(){
		
		
	}
}
