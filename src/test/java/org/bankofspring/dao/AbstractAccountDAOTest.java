package org.bankofspring.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.bankofspring.model.Account;
import org.junit.Test;

public abstract class AbstractAccountDAOTest {
	protected abstract AccountDAO getDao();
	
	@Test
	public void testGetAccount() {
		Account testAccount = getDao().getAccountByNumber( "account3" );
		assertEquals( 100L, testAccount.getAccountBalance() );
		assertEquals( "account3description", testAccount.getAccountDescription() );
		assertEquals( "account3", testAccount.getAccountNumber() );
		assertEquals( 1000000000, testAccount.getMaxBalanceAmount() );
	}
	
	@Test
	public void testGetAccountNotPresent() {
		assertNull( getDao().getAccountByNumber( "a count 3, ha ha ha" ) );
	}
	
	@Test
	public void testGetAccountNull() {
		assertNull( getDao().getAccountByNumber( null ) );
	}
	
	@Test
	public void testUpdateAccountBalance() {
		assertTrue( getDao().updateAccountBalance( getDao().getAccountByNumber( "account1" ), 808L ) );
		assertEquals( "Balance was not updated", 808L, getDao().getAccountByNumber( "account1" ).getAccountBalance() );
	}
	
	@Test
	public void testUpdateAccountBalanceNull() {
		assertFalse( getDao().updateAccountBalance( getDao().getAccountByNumber( "account1" ), null ) );
		assertEquals( "Balance was updated", 0L, getDao().getAccountByNumber( "account1" ).getAccountBalance() );
	}
	
	@Test
	public void testUpdateAccountBalanceNullAccount() {
		assertFalse( getDao().updateAccountBalance( null, 1024L ) );
	}
	
	@Test
	public void testCreateAccount() {
		Account account = new Account();
		account.setAccountNumber( "404" );
		account.setAccountDescription( "Mah account" );
		account.setAccountBalance( 123L );
		account.setMaxBalanceAmount( 8888888L );
		assertTrue( getDao().addNewAccount( account ) );
		
		Account savedAccount = getDao().getAccountByNumber( "404" );
		assertEquals( "Description mismatch", account.getAccountDescription(), savedAccount.getAccountDescription() );
		assertEquals( "Balance mismatch", account.getAccountBalance(), savedAccount.getAccountBalance() );
		assertEquals( "Max balance mismatch", account.getMaxBalanceAmount(), savedAccount.getMaxBalanceAmount() );
	}
	
	@Test
	public void testCreateAccountNull() {
		assertFalse( getDao().addNewAccount( null ) );
	}
	
	@Test
	public void testCreateAccountNullAccountNumber() {
		Account account = new Account();
		account.setAccountDescription( "No account number" );
		account.setAccountBalance( 123L );
		account.setMaxBalanceAmount( 8888888L );
		assertFalse( getDao().addNewAccount( account ) );
	}
	
	@Test
	public void testCreateAccountNullDescription() {
		Account account = new Account();
		account.setAccountNumber( "1777" );
		account.setAccountBalance( 123L );
		account.setMaxBalanceAmount( 8888888L );
		assertFalse( getDao().addNewAccount( account ) );
		assertNull( getDao().getAccountByNumber( "1777" ) );
	}
	
	@Test
	public void testCreateAccountNullBalance() {
		Account account = new Account();
		account.setAccountNumber( "1666" );
		account.setAccountDescription( "No balance or max balance" );
		assertTrue( getDao().addNewAccount( account ) );
		
		Account savedAccount = getDao().getAccountByNumber( "1666" );
		assertEquals( "Unexpected balance", 0L, savedAccount.getAccountBalance() );
		assertEquals( "Unexpected max balance", 0L, savedAccount.getMaxBalanceAmount() );
	}
}
