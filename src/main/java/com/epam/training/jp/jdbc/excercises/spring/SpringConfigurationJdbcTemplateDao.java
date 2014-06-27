package com.epam.training.jp.jdbc.excercises.spring;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.epam.training.jp.jdbc.excercises.dao.AddressDao;
import com.epam.training.jp.jdbc.excercises.dao.FoodDao;
import com.epam.training.jp.jdbc.excercises.dao.MenuDao;
import com.epam.training.jp.jdbc.excercises.dao.MenuFoodDao;
import com.epam.training.jp.jdbc.excercises.dao.RestaurantDao;
import com.epam.training.jp.jdbc.excercises.dao.jdbctemplateimpl.JdbcTemplateAddressDao;
import com.epam.training.jp.jdbc.excercises.dao.jdbctemplateimpl.JdbcTemplateFoodDao;
import com.epam.training.jp.jdbc.excercises.dao.jdbctemplateimpl.JdbcTemplateMenuDao;
import com.epam.training.jp.jdbc.excercises.dao.jdbctemplateimpl.JdbcTemplateMenuFoodDao;
import com.epam.training.jp.jdbc.excercises.dao.jdbctemplateimpl.JdbcTemplateRestaurantDao;

@Configuration
public class SpringConfigurationJdbcTemplateDao {

	@Bean
	AddressDao addressDao(DataSource dataSource) {
		return new JdbcTemplateAddressDao(dataSource);
	}

	@Bean
	RestaurantDao restaurantDao(DataSource dataSource) {
		return new JdbcTemplateRestaurantDao(dataSource);
	}
	
	@Bean
	FoodDao foodDao(DataSource dataSource) {
		return new JdbcTemplateFoodDao(dataSource);
	}
	
	@Bean
	MenuDao menuDao(DataSource dataSource) {
		return new JdbcTemplateMenuDao(dataSource);
	}
	
	@Bean
	MenuFoodDao menuFoodDao(DataSource dataSource) {
		return new JdbcTemplateMenuFoodDao(dataSource);
	}
	
}
