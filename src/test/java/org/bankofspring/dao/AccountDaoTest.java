package org.bankofspring.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.bankofspring.model.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:BankOfSpring.xml")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class AccountDaoTest {
	@Autowired
	AccountDao accountDao;
	
	@Test
	public void testSelectById() {
		Account account = accountDao.getAccountById( "00001" );
		
		assertEquals( "Account number", "00001", account.getAccountNumber() );
		assertEquals( "Account description", "Alice Aqua personal account", account.getAccountDescription() );
		assertEquals( "Account balance", 100000, account.getAccountBalance() );
		assertEquals( "Account max balance", 9999999, account.getMaxBalanceAmount() );
	}
	
	@Test
	public void testUpdateAccountBalance() {
		assertTrue( "Update failed", accountDao.updateAccountBalance( "00001", 200000 ) );
		
		long newBalance = accountDao.getAccountById( "00001" ).getAccountBalance();
		assertEquals( "New account balance", 200000, newBalance );
	}
	
	@Test
	public void testCreditAccount() {
		Account account = accountDao.getAccountById( "00001" );
		assertTrue( "Update failed", accountDao.creditAccount( account, 20000 ) );
		
		long newBalance = accountDao.getAccountById( "00001" ).getAccountBalance();
		assertEquals( "New account balance", 120000, newBalance );
	}
	
	@Test
	public void testDebitAccount() {
		Account account = accountDao.getAccountById( "00001" );
		assertTrue( "Update failed", accountDao.debitAccount( account, 20000 ) );
		
		long newBalance = accountDao.getAccountById( "00001" ).getAccountBalance();
		assertEquals( "New account balance", 80000, newBalance );
	}
}
