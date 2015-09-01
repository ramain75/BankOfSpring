package org.bankofspring.audit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;

import org.bankofspring.dao.AccountDAO;
import org.bankofspring.dao.CustomerDAO;
import org.bankofspring.model.Account;
import org.bankofspring.model.Customer;
import org.bankofspring.service.BankOfSpringService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/*
 * This test class tests it all together
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:BankOfSpring-ds.xml", "classpath:BankOfSpring.xml"})
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class BankOfSpringTimerTest {
	
	@Autowired
	@Qualifier("bankService")
	private BankOfSpringService service;
	
	@Autowired
	private BankOfSpringServiceTimer timer;
	
	@Autowired
	private CustomerDAO customerDAO;
	
	@Autowired
	@Qualifier("jdbcAccountDao")
	private AccountDAO accountDAO;
	
	private Account account1;
	private Account account3;
	
	private Customer customer1;
	
	@Before
	public void setup() {
		account1 = accountDAO.getAccountByNumber( "account1" );
		account3 = accountDAO.getAccountByNumber( "account3" );
		
		customer1 = customerDAO.getCustomerById(1);
	}
	
	@Test
	public void testDebit() {
		service.withdraw( customer1, account3, 100L );
		
		Iterator<AuditResult> timedActions = timer.getActions();
		assertNotNull( "Timer actions null", timedActions );
		assertTrue( "Timer actions empty", timedActions.hasNext() );
		
		AuditResult audit = timedActions.next();
		assertEquals( "Action name mismatch", "withdraw", audit.getActionName() );
		assertSame( "User mismatch", customer1, audit.getUser() );
		assertTrue( "Expected success", audit.wasSuccess() );
		assertNull( "Expected no error", audit.getThrown() );
		assertTrue( "Start time should be > 0", audit.getStart() > 0 );
		assertTrue( "Duration should be >= 0", audit.getDuration() >= 0 );
		
		assertFalse( "Too many timer actions", timedActions.hasNext() );
	}
	
	@Test
	public void testDebitWithToAccount() {
		service.transfer( customer1, account3, account1, 100L );
		
		Iterator<AuditResult> timedActions = timer.getActions();
		assertNotNull( "Timer actions null", timedActions );
		assertTrue( "Timer actions empty", timedActions.hasNext() );
		
		AuditResult audit = timedActions.next();
		assertEquals( "Action name mismatch", "transfer", audit.getActionName() );
		assertSame( "User mismatch", customer1, audit.getUser() );
		assertTrue( "Expected success", audit.wasSuccess() );
		assertNull( "Expected no error", audit.getThrown() );
		assertTrue( "Start time should be > 0", audit.getStart() > 0 );
		assertTrue( "Duration should be >= 0", audit.getDuration() >= 0 );
		
		assertFalse( "Too many timer actions", timedActions.hasNext() );
	}
	
	@Test
	public void testDebitFails() {;
		service.withdraw( customer1, account1, 100L );
		
		Iterator<AuditResult> timedActions = timer.getActions();
		assertNotNull( "Timer actions null", timedActions );
		assertTrue( "Timer actions empty", timedActions.hasNext() );
		
		AuditResult audit = timedActions.next();
		assertEquals( "Action name mismatch", "withdraw", audit.getActionName() );
		assertSame( "User mismatch", customer1, audit.getUser() );
		assertFalse( "Expected failure", audit.wasSuccess() );
		assertNull( "Expected no error", audit.getThrown() );
		assertTrue( "Start time should be > 0", audit.getStart() > 0 );
		assertTrue( "Duration should be >= 0", audit.getDuration() >= 0 );
		
		assertFalse( "Too many timer actions", timedActions.hasNext() );
	}
	
	@Test
	public void testDebitCrashes() {
		Throwable error = null;
		
		try {
			service.withdraw( customer1, account1, -100 );
		}
		catch ( Throwable t ) {
			error = t;
		}
		
		Iterator<AuditResult> timedActions = timer.getActions();
		assertNotNull( "Timer actions null", timedActions );
		assertTrue( "Timer actions empty", timedActions.hasNext() );
		
		AuditResult audit = timedActions.next();
		assertEquals( "Action name mismatch", "withdraw", audit.getActionName() );
		assertSame( "User mismatch", customer1, audit.getUser() );
		assertSame( "Expected same throwable as caught", error, audit.getThrown() );
		assertEquals( "Unexpected error class", RuntimeException.class, error.getClass() );
		assertEquals( "Unexpected error message", "amount must be positive", error.getMessage() );
		assertFalse( "Expected failure", audit.wasSuccess() );
		assertTrue( "Start time should be > 0", audit.getStart() > 0 );
		assertTrue( "Duration should be >= 0", audit.getDuration() >= 0 );
		
		assertFalse( "Too many timer actions", timedActions.hasNext() );
	}
	
	@Test
	public void testCredit() {
		service.deposit( customer1, account1, 100L );
		
		Iterator<AuditResult> timedActions = timer.getActions();
		assertNotNull( "Timer actions null", timedActions );
		assertTrue( "Timer actions empty", timedActions.hasNext() );
		
		AuditResult audit = timedActions.next();
		assertEquals( "Action name mismatch", "deposit", audit.getActionName() );
		assertSame( "User mismatch", customer1, audit.getUser() );
		assertTrue( "Expected success", audit.wasSuccess() );
		assertNull( "Expected no error", audit.getThrown() );
		assertTrue( "Start time should be > 0", audit.getStart() > 0 );
		assertTrue( "Duration should be >= 0", audit.getDuration() >= 0 );
		
		assertFalse( "Too many timer actions", timedActions.hasNext() );
	}
	
	@Test
	public void testCreditWithToAccount() {
		service.transfer( customer1, account3, account1, 100L );
		
		Iterator<AuditResult> timedActions = timer.getActions();
		assertNotNull( "Timer actions null", timedActions );
		assertTrue( "Timer actions empty", timedActions.hasNext() );
		
		AuditResult audit = timedActions.next();
		assertEquals( "Action name mismatch", "transfer", audit.getActionName() );
		assertSame( "User mismatch", customer1, audit.getUser() );
		assertTrue( "Expected success", audit.wasSuccess() );
		assertNull( "Expected no error", audit.getThrown() );
		assertTrue( "Start time should be > 0", audit.getStart() > 0 );
		assertTrue( "Duration should be >= 0", audit.getDuration() >= 0 );
		
		assertFalse( "Too many timer actions", timedActions.hasNext() );
	}
	
	@Test
	public void testCreditFails() {
		service.deposit( customer1, account1, Long.MAX_VALUE );
		
		Iterator<AuditResult> timedActions = timer.getActions();
		assertNotNull( "Timer actions null", timedActions );
		assertTrue( "Timer actions empty", timedActions.hasNext() );
		
		AuditResult audit = timedActions.next();
		assertEquals( "Action name mismatch", "deposit", audit.getActionName() );
		assertSame( "User mismatch", customer1, audit.getUser() );
		assertFalse( "Expected failure", audit.wasSuccess() );
		assertNull( "Expected no error", audit.getThrown() );
		assertTrue( "Start time should be > 0", audit.getStart() > 0 );
		assertTrue( "Duration should be >= 0", audit.getDuration() >= 0 );
		
		assertFalse( "Too many timer actions", timedActions.hasNext() );
	}
	
	@Test
	public void testCreditCrashes() {
		Throwable error = null;
		
		try {
			service.deposit( customer1, account1, -100 );
		}
		catch ( Throwable t ) {
			error = t;
		}
		
		Iterator<AuditResult> timedActions = timer.getActions();
		assertNotNull( "Timer actions null", timedActions );
		assertTrue( "Timer actions empty", timedActions.hasNext() );
		
		AuditResult audit = timedActions.next();
		assertEquals( "Action name mismatch", "deposit", audit.getActionName() );
		assertSame( "User mismatch", customer1, audit.getUser() );
		assertSame( "Expected same throwable as caught", error, audit.getThrown() );
		assertEquals( "Unexpected error class", RuntimeException.class, error.getClass() );
		assertEquals( "Unexpected error message", "amount must be positive", error.getMessage() );
		assertFalse( "Expected failure", audit.wasSuccess() );
		assertTrue( "Start time should be > 0", audit.getStart() > 0 );
		assertTrue( "Duration should be >= 0", audit.getDuration() >= 0 );
		
		assertFalse( "Too many timer actions", timedActions.hasNext() );
	}
	
	@Test
	public void testNoActions() {
		Iterator<AuditResult> timedActions = timer.getActions();
		assertNotNull( "Timer actions null", timedActions );
		assertFalse( "Too many timer actions", timedActions.hasNext() );
	}
	
	@Test
	public void testMultipleActions() {
		service.withdraw( customer1, account1, 100L );
		service.deposit( customer1, account1, 1000L );
		service.withdraw( customer1, account1, 100L );
		
		Iterator<AuditResult> timedActions = timer.getActions();
		assertNotNull( "Timer actions null", timedActions );
		
		assertTrue( "Timer actions empty", timedActions.hasNext() );
		assertEquals( "Action name mismatch", "withdraw", timedActions.next().getActionName() );
		
		assertTrue( "Too few timer actions", timedActions.hasNext() );
		assertEquals( "Action name mismatch", "deposit", timedActions.next().getActionName() );
		
		assertTrue( "Too few timer actions", timedActions.hasNext() );
		assertEquals( "Action name mismatch", "withdraw", timedActions.next().getActionName() );
		
		assertFalse( "Too many timer actions", timedActions.hasNext() );
	}
}
