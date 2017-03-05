package com.pinnpark.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pinnpark.core.City;

public interface CityRepository extends MongoRepository<City, String> {

}
