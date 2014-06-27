package com.epam.training.jp.jdbc.excercises.dao.jdbcimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import com.epam.training.jp.jdbc.excercises.dao.RestaurantDao;
import com.epam.training.jp.jdbc.excercises.domain.Address;
import com.epam.training.jp.jdbc.excercises.domain.Food;
import com.epam.training.jp.jdbc.excercises.domain.Restaurant;
import com.epam.training.jp.jdbc.excercises.dto.RestaurantWithAddress;

public class JdbcRestaurantDao extends GenericJdbcDao implements RestaurantDao {

	public JdbcRestaurantDao(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public List<Food> getFoodsAvailable(Date date, String restaurantName) {
		String sql = "SELECT FOOD.ID, FOOD.CALORIES, FOOD.ISVEGAN, FOOD.NAME, FOOD.PRICE "
				+ "FROM FOOD "
				+ "JOIN MENU_FOOD ON FOOD.ID = MENU_FOOD.FOOD_ID "
				+ "JOIN MENU ON MENU.ID = MENU_FOOD.MENU_ID "
				+ "JOIN RESTAURANT ON RESTAURANT.ID = MENU.RESTAURANT_ID "
				+ "WHERE RESTAURANT.NAME = ? AND ? BETWEEN FROMDATE AND TODATE";


		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1,  restaurantName);
			ps.setDate(2, new java.sql.Date(date.getTime()));

			List<Food> foods = new ArrayList<Food>();
			try (ResultSet rs = ps.executeQuery()) {
				Food food;
				while (rs.next()) {
					food = new Food();					
					food.setId(rs.getInt(1));
					food.setCalories(rs.getInt(2));
					food.setVegan(rs.getBoolean(3));
					food.setName(rs.getString(4));
					food.setPrice(rs.getInt(5));
					foods.add(food);
				}
			}
			return foods;
		} 
		catch (SQLException ex) {
			throw new RuntimeException(ex);
		}

	}

	@Override
	public List<RestaurantWithAddress> getAllRestaurantsWithAddress() {
		List<RestaurantWithAddress> restaurantWithAddressList = new ArrayList<RestaurantWithAddress>();
		
		String sql = "SELECT r.ID as restaurant_id, r.NAME, a.ID, a.COUNTRY, a.ZIPCODE, a.CITY, a.STREET " +
				"FROM restaurant as r " +
				"JOIN address as a on (r.ADDRESS_ID=a.ID)";
		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Restaurant restaurant = new Restaurant();
				Address address = new Address();
				
				restaurant.setId(rs.getInt(1));
				restaurant.setName(rs.getString(2));
				restaurant.setAddressId(rs.getInt(3));
				
				address.setId(rs.getInt(3));
				address.setCountry(rs.getString(4));
				address.setZipCode(rs.getString(5));
				address.setCity(rs.getString(6));
				address.setStreet(rs.getString(7));
				
				RestaurantWithAddress rwa = new RestaurantWithAddress(restaurant, address);
				restaurantWithAddressList.add(rwa);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return restaurantWithAddressList;
	}
	



}
