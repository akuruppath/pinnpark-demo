package com.pinnpark.resourceprocessors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

import com.pinnpark.controllers.CityController;
import com.pinnpark.controllers.ParkingLocationController;
import com.pinnpark.core.ParkingLocation;

@Component
public class ParkingLocationResourceProcessor implements ResourceProcessor<Resource<ParkingLocation>> {

	@Override
	public Resource<ParkingLocation> process(Resource<ParkingLocation> resource) {
		ParkingLocation location = resource.getContent();

		resource.add(
				linkTo(methodOn(ParkingLocationController.class).getParkingLocation(location.getId())).withSelfRel());
		resource.add(linkTo(methodOn(CityController.class).getCity(location.getCity().getId())).withRel("city"));
		return resource;
	}

}
