package org.bankofspring.dao.hibernate;

import java.util.List;

import org.bankofspring.dao.AccountDAO;
import org.bankofspring.model.Account;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

/**
 * This DAO uses Hibernate CRUD methods for account table
 * 
 * @author axp
 */
@Repository("hibernateAccountDao")
public class AccountDAOHibernateImpl extends HibernateDaoSupport implements AccountDAO {
	@Autowired
	public void setupSessionFactory( SessionFactory sessionFactory ) {
		setSessionFactory( sessionFactory );
	}
	
	public void create( Account account ) {
		getHibernateTemplate().save( account );
	}
	
	public Account read( String accountNumber ) {
		return getHibernateTemplate().get( Account.class, accountNumber );
	}
	
	public void update( Account account ) {
		getHibernateTemplate().update( account );
	}
	
	public void delete( Account account ) {
		getHibernateTemplate().delete( account );
	}

	@Override
	public Account getAccountByNumber( String accountNumber ) {
		if ( accountNumber == null ) {
			return null;
		}
		
		return read( accountNumber );
	}

	@Override
	public boolean updateAccountBalance( Account account, Long amount ) {
		if ( ( account == null ) || ( amount == null ) ) {
			return false;
		}
		
		account.setAccountBalance( amount );
		update( account );
		return true;
	}

	@Override
	public boolean addNewAccount( Account account ) {
		if ( ( account == null ) ||
				( account.getAccountNumber() == null ) ||
				( account.getAccountDescription() == null ) ) {
			
			return false;
		}
		
		create( account );
		return true;
	}

	@Override
	public List<Account> getCustomerAccounts(Integer customer) {
		throw new RuntimeException("Not implemented");
	}
}
