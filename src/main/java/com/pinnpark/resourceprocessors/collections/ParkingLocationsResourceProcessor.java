package com.pinnpark.resourceprocessors.collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import com.pinnpark.core.ParkingLocation;
import com.pinnpark.resourceprocessors.ParkingLocationResourceProcessor;

@Component
public class ParkingLocationsResourceProcessor implements ResourceProcessor<Resources<Resource<ParkingLocation>>>{

	@Autowired
	private ParkingLocationResourceProcessor parkingLocationResourceProcessor;
	
	@Override
	public Resources<Resource<ParkingLocation>> process(Resources<Resource<ParkingLocation>> resources) {
		resources.forEach(resource -> {
			parkingLocationResourceProcessor.process(resource);
		});
		return resources;
	}

}
