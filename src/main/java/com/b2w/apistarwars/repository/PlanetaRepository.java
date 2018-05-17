package com.b2w.apistarwars.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.b2w.apistarwars.models.Planeta;

public interface PlanetaRepository extends MongoRepository<Planeta, String> {

	

}
