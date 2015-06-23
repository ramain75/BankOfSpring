package org.bankofspring.dao;

import org.bankofspring.model.AccountTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;


/**
 * @author slc
 *
 */
@Repository
public class AccountTransactionDAOImpl extends AbstractDao implements AccountTransactionDAO {

	@Autowired
	private SimpleJdbcTemplate jdbc;
	
	/**
	 * Create an account transaction object in the DB
	 */
	public boolean create( AccountTransaction accountTransaction ) {	
		try{
			getHibernateTemplate().saveOrUpdate(accountTransaction);
			return true;
		}catch(DataAccessException dae){
			dae.printStackTrace();
			return false;
		}	
	}
}
