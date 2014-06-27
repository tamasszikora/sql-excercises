package com.epam.training.jp.jdbc.excercises.spring;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.epam.training.jp.jdbc.excercises.dao.AddressDao;
import com.epam.training.jp.jdbc.excercises.dao.FoodDao;
import com.epam.training.jp.jdbc.excercises.dao.MenuDao;
import com.epam.training.jp.jdbc.excercises.dao.MenuFoodDao;
import com.epam.training.jp.jdbc.excercises.dao.RestaurantDao;
import com.epam.training.jp.jdbc.excercises.dao.jdbcimpl.JdbcAddressDao;
import com.epam.training.jp.jdbc.excercises.dao.jdbcimpl.JdbcFoodDao;
import com.epam.training.jp.jdbc.excercises.dao.jdbcimpl.JdbcMenuDao;
import com.epam.training.jp.jdbc.excercises.dao.jdbcimpl.JdbcMenuFoodDao;
import com.epam.training.jp.jdbc.excercises.dao.jdbcimpl.JdbcRestaurantDao;

@Configuration
public class SpringConfigurationJdbcDao {

	@Bean
	AddressDao addressDao(DataSource dataSource) {
		return new JdbcAddressDao(dataSource);
	}

	@Bean
	RestaurantDao restaurantDao(DataSource dataSource) {
		return new JdbcRestaurantDao(dataSource);
	}
	
	@Bean
	FoodDao foodDao(DataSource dataSource) {
		return new JdbcFoodDao(dataSource);
	}
	
	@Bean
	MenuDao menuDao(DataSource dataSource) {
		return new JdbcMenuDao(dataSource);
	}
	
	@Bean
	MenuFoodDao menuFoodDao(DataSource dataSource) {
		return new JdbcMenuFoodDao(dataSource);
	}
	
}
