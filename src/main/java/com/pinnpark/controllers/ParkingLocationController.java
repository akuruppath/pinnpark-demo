package com.pinnpark.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pinnpark.core.City;
import com.pinnpark.core.ParkingLocation;
import com.pinnpark.repositories.CityRepository;
import com.pinnpark.repositories.ParkingLocationRepository;
import com.pinnpark.resourceprocessors.ParkingLocationResourceProcessor;
import com.pinnpark.resourceprocessors.collections.ParkingLocationsResourceProcessor;

@RequestMapping("/pinnpark")
@RestController
public class ParkingLocationController {

	@Autowired
	private ParkingLocationRepository parkingLocationRepository;

	@Autowired
	private ParkingLocationResourceProcessor locationResourceProcessor;

	@Autowired
	private ParkingLocationsResourceProcessor locationsResourceProcessor;

	@Autowired
	private CityRepository cityRepository;

	@RequestMapping(method = RequestMethod.GET, path = "/cities/{city-id}/parking-locations", produces = "application/hal+json")
	public ResponseEntity<Resources<Resource<ParkingLocation>>> getAllParkingLocations(
			@PathVariable(value = "city-id") final String cityId) {
		List<ParkingLocation> locations = parkingLocationRepository.findByCity(cityId);
		return new ResponseEntity<>(locationsResourceProcessor.process(Resources.wrap(locations)), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/parking-locations/{location-id}", produces = "application/hal+json")
	public ResponseEntity<Resource<ParkingLocation>> getParkingLocation(
			@PathVariable(value = "location-id") final String id) {
		ParkingLocation location = parkingLocationRepository.findOne(id);
		return new ResponseEntity<>(locationResourceProcessor.process(new Resource<ParkingLocation>(location)),
				HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/cities/{city-id}/parking-locations", produces = "application/hal+json", consumes = "application/hal+json")
	public ResponseEntity<Resource<ParkingLocation>> createParkingLocation(
			@PathVariable(value = "city-id") final String cityId, @RequestBody ParkingLocation location) {
		City city = cityRepository.findOne(cityId);
		location.setCity(city);
		ParkingLocation save = parkingLocationRepository.save(location);
		return new ResponseEntity<>(locationResourceProcessor.process(new Resource<ParkingLocation>(save)),
				HttpStatus.CREATED);
	}
}
