package com.epam.training.jp.jdbc.excercises.dao.jdbctemplateimpl;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.epam.training.jp.jdbc.excercises.dao.AddressDao;
import com.epam.training.jp.jdbc.excercises.domain.Address;

public class JdbcTemplateAddressDao extends JdbcDaoSupport implements AddressDao {

	public JdbcTemplateAddressDao(DataSource dataSource) {		
		setDataSource(dataSource);
	}

	@Override
	public void save(Address address) {
		JdbcTemplate template = getJdbcTemplate();
		template.setDataSource(getDataSource());
		SimpleJdbcInsert insertAddress = new SimpleJdbcInsert(getDataSource()).withTableName("address").usingGeneratedKeyColumns("ID");
		
		Map<String, Object> parameters = new HashMap<String, Object>(4);
		parameters.put("COUNTRY", address.getCountry());
		parameters.put("CITY", address.getCity());
		parameters.put("STREET", address.getStreet());
		parameters.put("ZIPCODE", address.getZipCode());
		
		int newId = insertAddress.executeAndReturnKey(parameters).intValue();
		address.setId(newId);
		
		/*NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(getDataSource());
		SqlParameterSource sqlPS = new BeanPropertySqlParameterSource(address);
		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		
		String sql = "insert into address (COUNTRY, CITY, STREET, ZIPCODE)" +
				" values (:country, :city, :street, :zipCode)";
		
		template.update(sql, sqlPS, generatedKeyHolder);
		address.setId(generatedKeyHolder.getKey().intValue());*/
	}

}
