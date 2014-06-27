package com.epam.training.jp.jdbc.excercises.dto;

import com.epam.training.jp.jdbc.excercises.domain.Address;
import com.epam.training.jp.jdbc.excercises.domain.Restaurant;

public class RestaurantWithAddress {

	private final Restaurant restaurant;
	private final Address address;
	
	public RestaurantWithAddress(Restaurant restaurant, Address address) {
		super();
		this.restaurant = restaurant;
		this.address = address;
	}

	public int getId() {
		return restaurant.getId();
	}

	public String getName() {
		return restaurant.getName();
	}

	public String getStreet() {
		return address.getStreet();
	}

	public String getCity() {
		return address.getCity();
	}

	public String getCountry() {
		return address.getCountry();
	}

	public String getZipCode() {
		return address.getZipCode();
	}

	@Override
	public String toString() {
		return "RestaurantWithAddress [getId()=" + getId() + ", getName()=" + getName() + ", getStreet()=" + getStreet() + ", getCity()=" + getCity()
				+ ", getCountry()=" + getCountry() + ", getZipCode()=" + getZipCode() + "]";
	}
	
	
	
	
	
	
	
}
