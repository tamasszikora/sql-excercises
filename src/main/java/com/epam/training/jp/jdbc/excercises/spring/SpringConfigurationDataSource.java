package com.epam.training.jp.jdbc.excercises.spring;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.epam.training.jp.jdbc.excercises.helper.DatabaseCreator;

@Configuration
public class SpringConfigurationDataSource {
	private static String dbUrl = "jdbc:mysql://localhost/sql_excercise";
	private static String username = "root";
	private static String password = "root";
	
	@Bean
	public DataSource dataSource() {
		return new DriverManagerDataSource(dbUrl, username, password);
	}
	
	@Bean
	public DatabaseCreator databaseCreator(DataSource dataSource) {
		return new DatabaseCreator(dataSource);
	}
	
}
