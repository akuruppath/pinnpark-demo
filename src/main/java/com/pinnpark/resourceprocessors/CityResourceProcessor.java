package com.pinnpark.resourceprocessors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

import com.pinnpark.controllers.CityController;
import com.pinnpark.controllers.ParkingLocationController;
import com.pinnpark.core.City;

@Component
public class CityResourceProcessor implements ResourceProcessor<Resource<City>> {

	@Override
	public Resource<City> process(Resource<City> resource) {
		City city = resource.getContent();

		resource.add(linkTo(methodOn(CityController.class).getCity(city.getId())).withSelfRel());
		resource.add(linkTo(methodOn(CityController.class).getAllCities()).withRel("cities"));
		resource.add(linkTo(methodOn(ParkingLocationController.class).getAllParkingLocations(city.getId()))
				.withRel("parking-locations"));
		return resource;
	}

}
