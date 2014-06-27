package com.epam.training.jp.jdbc.excercises.dao.jdbcimpl;

import javax.sql.DataSource;

public abstract class GenericJdbcDao {

	protected final DataSource dataSource;

	public GenericJdbcDao(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
		
}
