package com.epam.training.jp.jdbc.excercises.dao;

import java.util.Date;
import java.util.List;

import com.epam.training.jp.jdbc.excercises.domain.Food;
import com.epam.training.jp.jdbc.excercises.dto.RestaurantWithAddress;

public interface RestaurantDao {

	List<Food> getFoodsAvailable(Date date, String restaurantName);
	
	List<RestaurantWithAddress> getAllRestaurantsWithAddress();
}
