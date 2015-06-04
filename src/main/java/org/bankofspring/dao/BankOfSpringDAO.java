package org.bankofspring.dao;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class BankOfSpringDAO {
	
	@Autowired
	@Qualifier("bankOfSpringDS")
	private DataSource dataSource;
	
	public DataSource getDataSource() {
		return dataSource;
	}
	
}
