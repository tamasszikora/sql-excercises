package com.epam.training.jp.jdbc.excercises.dao.jdbctemplateimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.epam.training.jp.jdbc.excercises.dao.RestaurantDao;
import com.epam.training.jp.jdbc.excercises.domain.Address;
import com.epam.training.jp.jdbc.excercises.domain.Food;
import com.epam.training.jp.jdbc.excercises.domain.Restaurant;
import com.epam.training.jp.jdbc.excercises.dto.RestaurantWithAddress;

public class JdbcTemplateRestaurantDao extends JdbcDaoSupport implements RestaurantDao {

	public JdbcTemplateRestaurantDao(DataSource dataSource) {
		setDataSource(dataSource);
	}

	@Override
	public List<Food> getFoodsAvailable(Date date, String restaurantName) {
		JdbcTemplate template = new JdbcTemplate(getDataSource());
		String sql = "SELECT FOOD.ID, FOOD.CALORIES, FOOD.ISVEGAN, FOOD.NAME, FOOD.PRICE "
				+ "FROM FOOD "
				+ "JOIN MENU_FOOD ON FOOD.ID = MENU_FOOD.FOOD_ID "
				+ "JOIN MENU ON MENU.ID = MENU_FOOD.MENU_ID "
				+ "JOIN RESTAURANT ON RESTAURANT.ID = MENU.RESTAURANT_ID "
				+ "WHERE RESTAURANT.NAME = ? AND ? BETWEEN FROMDATE AND TODATE";
		
		RowMapper<Food> rowMapper = new RowMapper<Food>() {
			
			@Override
			public Food mapRow(ResultSet rs, int rowNum) throws SQLException {
				Food food = new Food();
				food.setId(rs.getInt("ID"));
				food.setCalories(rs.getInt("CALORIES"));
				food.setVegan(rs.getBoolean("ISVEGAN"));
				food.setName(rs.getString("NAME"));
				food.setPrice(rs.getInt("PRICE"));
				return food;
			}
		};
		
		List<Food> foods = template.query(sql, rowMapper, restaurantName, new java.sql.Date(date.getTime()));
		
		return foods;
	}

	@Override
	public List<RestaurantWithAddress> getAllRestaurantsWithAddress() {
		JdbcTemplate template = new JdbcTemplate(getDataSource());
		String sql = "SELECT r.ID as restaurant_id, r.NAME, a.ID, a.COUNTRY, a.ZIPCODE, a.CITY, a.STREET " +
				"FROM restaurant as r " +
				"JOIN address as a on (r.ADDRESS_ID=a.ID)";
		
		RowMapper<RestaurantWithAddress> rowMapper = new RowMapper<RestaurantWithAddress>() {
			
			@Override
			public RestaurantWithAddress mapRow(ResultSet rs, int rowNum) throws SQLException {
				Restaurant restaurant = new Restaurant();
				Address address = new Address();
				
				restaurant.setId(rs.getInt("restaurant_id"));
				restaurant.setName(rs.getString("NAME"));
				restaurant.setAddressId(rs.getInt("ID"));
				
				address.setId(rs.getInt("ID"));
				address.setCountry(rs.getString("COUNTRY"));
				address.setZipCode(rs.getString("ZIPCODE"));
				address.setCity(rs.getString("CITY"));
				address.setStreet(rs.getString("STREET"));
				
				RestaurantWithAddress restaurantWithAddress = new RestaurantWithAddress(restaurant, address);
				
				return restaurantWithAddress;
			}
		};
		
		List<RestaurantWithAddress> restaurantWithAddressList = template.query(sql, rowMapper);
		
		return restaurantWithAddressList;
	}
	



}
