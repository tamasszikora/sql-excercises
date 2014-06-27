package com.epam.training.jp.jdbc.excercises.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.epam.training.jp.jdbc.excercises.dao.AddressDao;
import com.epam.training.jp.jdbc.excercises.dao.FoodDao;
import com.epam.training.jp.jdbc.excercises.dao.MenuDao;
import com.epam.training.jp.jdbc.excercises.dao.MenuFoodDao;
import com.epam.training.jp.jdbc.excercises.dao.RestaurantDao;
import com.epam.training.jp.jdbc.excercises.service.RestaurantService;

@Configuration
public class SpringConfigurationService {
	
	@Bean
	public RestaurantService restaurantService(AddressDao addressDao, RestaurantDao restaurantDao, FoodDao foodDao, MenuDao menuDao, MenuFoodDao menuFoodDao) {
		return new RestaurantService(addressDao, restaurantDao, foodDao, menuDao, menuFoodDao);
	}	
}
