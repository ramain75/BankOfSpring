package org.bankofspring.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.bankofspring.dao.AccountDAO;
import org.bankofspring.model.Account;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * This DAO uses JPA CRUD methods for account table
 * 
 * @author axp
 */
@Repository("jpaAccountDao")
@Transactional(propagation = Propagation.REQUIRED)
public class AccountDAOJPAImpl implements AccountDAO {
	@PersistenceContext
	private EntityManager entityManager;
	
	public EntityManager getEntityManager() {
		return this.entityManager;
	}
	
	public void setEntityManagerFactory( EntityManager entityManager ) {
		this.entityManager = entityManager;
	}
	
	public void create( Account account ) {
		this.entityManager.persist( account );
	}
	
	public Account read( String accountNumber ) {
		return this.entityManager.find( Account.class, accountNumber );
	}
	
	public void update( Account account ) {
		this.entityManager.merge( account );
	}
	
	public void delete( Account account ) {
		this.entityManager.remove( account );
	}
	
	@Override
	public boolean debitAccount( Account account, Long amount ) {
		account.setAccountBalance( account.getAccountBalance() - amount );
		update( account );
		return true;
	}

	@Override
	public boolean creditAccount( Account account, Long amount ) {
		account.setAccountBalance( account.getAccountBalance() + amount );
		update( account );
		return true;
	}

	@Override
	public Account getAccountByName( String accountName ) {
		return read( accountName );
	}
}
