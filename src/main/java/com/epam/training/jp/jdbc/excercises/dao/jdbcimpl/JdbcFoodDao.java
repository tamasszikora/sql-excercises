package com.epam.training.jp.jdbc.excercises.dao.jdbcimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import com.epam.training.jp.jdbc.excercises.dao.FoodDao;
import com.epam.training.jp.jdbc.excercises.domain.Food;

public class JdbcFoodDao extends GenericJdbcDao implements FoodDao {

	public JdbcFoodDao(DataSource dataSource) {
		super(dataSource);
	}
	
	
	@Override
	public Food findFoodByName(String name) {
		Food food = null; 
		String sql = "SELECT food.ID, food.NAME, food.ISVEGAN, food.CALORIES, food.PRICE " +
				"FROM food " +
				"WHERE food.name=?";
		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, name);
			
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				food = new Food(); 
				food.setId(rs.getInt(1));
				food.setName(rs.getString(2));
				food.setVegan(rs.getInt(3) == 0 ? false : true);
				food.setCalories(rs.getInt(4));
				food.setPrice(rs.getInt(5));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return food;
	}
	
	@Override
	public void updateFoodPriceByName(String name, int newPrice) {
		String sql = "UPDATE food SET price=? where name like ?";
		try (Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setString(1, name);
			ps.setInt(2, newPrice);
			
			ps.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

	}


	@Override
	public void save(List<Food> foods) {
		String sql = "INSERT INTO food (CALORIES, ISVEGAN, NAME, PRICE) VALUES (?, ?, ?, ?)";
		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
			conn.setAutoCommit(false);
			for(Food f : foods) {
				ps.setInt(1, f.getCalories());
				ps.setBoolean(2, f.isVegan());
				ps.setString(3, f.getName());
				ps.setInt(4, f.getPrice());
				ps.addBatch();
			}
			ps.executeBatch();
			conn.commit();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
