package com.epam.training.jp.jdbc.excercises.dao.jdbctemplateimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.epam.training.jp.jdbc.excercises.dao.FoodDao;
import com.epam.training.jp.jdbc.excercises.domain.Food;

public class JdbcTemplateFoodDao extends JdbcDaoSupport implements FoodDao {

	public JdbcTemplateFoodDao(DataSource dataSource) {
		setDataSource(dataSource);
	}

	@Override
	public Food findFoodByName(String name) {
		JdbcTemplate template = getJdbcTemplate();
		template.setDataSource(getDataSource());
		String sql = "select ID, CALORIES, ISVEGAN, NAME, PRICE from food where NAME=?";
		
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
		
		Food food = template.queryForObject(sql, rowMapper, name);
		
		return food;
	}

	@Override
	public void updateFoodPriceByName(String name, int newPrice) {
		
		JdbcTemplate template = getJdbcTemplate();
		template.setDataSource(getDataSource());
		
		String sql = "update food set PRICE=? where NAME=?";
		
		template.update(sql, newPrice, name);
	}

	@Override
	public void save(List<Food> foods) {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(getDataSource());
		SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(foods.toArray());
		
		String sql = "insert into food (CALORIES, ISVEGAN, NAME, PRICE) " +
				"values (:calories, :isVegan, :name, :price)";
		template.batchUpdate(sql, batch);
	}

	
}
