package com.pinnpark.resourceprocessors.collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import com.pinnpark.core.City;
import com.pinnpark.resourceprocessors.CityResourceProcessor;

@Component
public class CitiesResourceProcessor implements ResourceProcessor<Resources<Resource<City>>> {

	@Autowired
	private CityResourceProcessor cityResourceProcessor;

	@Override
	public Resources<Resource<City>> process(Resources<Resource<City>> resources) {
		resources.forEach(resource -> {
			cityResourceProcessor.process(resource);
		});
		return resources;
	}
}
