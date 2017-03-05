package com.pinnpark.core;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class ParkingLocation {

	//TODO: what are the exact attributes that define a location ???
	@Id
	private String id;
	
	private String name;
	
	private String address;
	
	private int freeSpots;
		
	private int totalSpots;
	
	@DBRef
	private City city;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getFreeSpots() {
		return freeSpots;
	}

	public void setFreeSpots(int freeSpots) {
		this.freeSpots = freeSpots;
	}

	public int getTotalSpots() {
		return totalSpots;
	}

	public void setTotalSpots(int totalSpots) {
		this.totalSpots = totalSpots;
	}


	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
}
