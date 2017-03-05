package com.pinnpark.controllers;

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
import com.pinnpark.repositories.CityRepository;
import com.pinnpark.resourceprocessors.CityResourceProcessor;
import com.pinnpark.resourceprocessors.collections.CitiesResourceProcessor;

@RequestMapping("/pinnpark")
@RestController
public class CityController {

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private CityResourceProcessor cityResourceProcessor;

	@Autowired
	private CitiesResourceProcessor citiesResourceProcessor;

	@RequestMapping(method = RequestMethod.GET, path = "/cities", produces = "application/hal+json")
	public ResponseEntity<Resources<Resource<City>>> getAllCities() {
		Resources<Resource<City>> cities = Resources.wrap(cityRepository.findAll());
		return new ResponseEntity<>(citiesResourceProcessor.process(cities), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/cities/{city-id}", produces = "application/hal+json")
	public ResponseEntity<Resource<City>> getCity(@PathVariable(value = "city-id") final String id) {
		City city = cityRepository.findOne(id);
		return new ResponseEntity<>(cityResourceProcessor.process(new Resource<City>(city)), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/cities", produces = "application/hal+json", consumes = "application/hal+json")
	public ResponseEntity<Resource<City>> addCity(@RequestBody City city) {
		City save = cityRepository.save(city);
		return new ResponseEntity<>(cityResourceProcessor.process(new Resource<City>(save)), HttpStatus.CREATED);
	}
}
