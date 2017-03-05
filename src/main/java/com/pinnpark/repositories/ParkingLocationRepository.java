package com.pinnpark.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pinnpark.core.ParkingLocation;

public interface ParkingLocationRepository extends MongoRepository<ParkingLocation, String> {

	public List<ParkingLocation> findByCity(String cityId);
	
	public ParkingLocation findByCityAndId(String cityId, String locationId);
}
