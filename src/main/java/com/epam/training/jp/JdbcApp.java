package com.epam.training.jp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.epam.training.jp.jdbc.excercises.domain.Address;
import com.epam.training.jp.jdbc.excercises.domain.Food;
import com.epam.training.jp.jdbc.excercises.helper.DatabaseCreator;
import com.epam.training.jp.jdbc.excercises.service.RestaurantService;
import com.epam.training.jp.jdbc.excercises.spring.SpringConfigurationDataSource;
import com.epam.training.jp.jdbc.excercises.spring.SpringConfigurationJdbcDao;
import com.epam.training.jp.jdbc.excercises.spring.SpringConfigurationService;


public class JdbcApp {

	public static void main(String[] args) {
		JdbcApp app = new JdbcApp();
		app.run();
	}
	
	protected void run() {
		AbstractApplicationContext context = createSpringContext();
		
		RestaurantService restaurantService = context.getBean(RestaurantService.class);
		DatabaseCreator databaseCreator = context.getBean(DatabaseCreator.class);
		
		databaseCreator.createAndPopulateDatabase();
				
		Address address = new Address();
		address.setCity("Budapest");
		address.setCountry("HU");
		address.setStreet("Futo utca");
		address.setZipCode("1085");
				
		restaurantService.save(address);
		
		Food food1 = new Food();
		food1.setCalories(10);
		food1.setName("10CaloriesFood");
		food1.setPrice(10);
		food1.setVegan(false);
		
		Food food2 = new Food();
		food2.setCalories(1);
		food2.setName("VeganFood");
		food2.setPrice(100);
		food2.setVegan(true);
		
		List<Food> foods = new ArrayList<Food>();
		foods.add(food1);
		foods.add(food2);
		
		restaurantService.save(foods);
		
		System.out.println("Retreived id of saved address: " + address.getId());
		
		System.out.println("Number of foods before delete menu #1: " + restaurantService.getFoodsAvailable(new Date(), "10 MINUTES").size());
		restaurantService.removeMenu(1);
		System.out.println("Number of foods after delete menu #1: " + restaurantService.getFoodsAvailable(new Date(), "10 MINUTES").size());
		
		System.out.println("Price before update food: " + restaurantService.findFoodByName("Tender lion").getPrice());
		restaurantService.updateFoodPriceByName("Tender lion", 7000);
		System.out.println("Price after update food: " + restaurantService.findFoodByName("Tender lion").getPrice());

		System.out.println("Restaurants: " + restaurantService.getAllRestaurantsWithAddress());
		
		restaurantService.removeMenu(1);		
		
		context.close();
		
	}
	
	protected AbstractApplicationContext createSpringContext() {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(
				SpringConfigurationDataSource.class,
				SpringConfigurationJdbcDao.class,
				SpringConfigurationService.class);
		return context;
	}
}
