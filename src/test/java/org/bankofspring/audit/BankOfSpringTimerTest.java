package org.bankofspring.audit;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.bankofspring.audit.AuditResult;
import org.bankofspring.audit.BankOfSpringServiceTimer;
import org.bankofspring.model.Account;
import org.bankofspring.model.Customer;
import org.bankofspring.service.BankOfSpringService;
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
@ContextConfiguration("classpath:BankOfSpring.xml")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class BankOfSpringTimerTest {
	
	@Autowired
	@Qualifier("bankService")
	private BankOfSpringService service;
	
	@Autowired
	private BankOfSpringServiceTimer timer;
	
	@Autowired
	@Qualifier("customer1")
	private Customer customer1;
	
	@Autowired
	@Qualifier("customer2")
	private Customer customer2;
	
	@Test
	public void testDebit() {
		Account account1 = customer1.getAccount( "account1" );
		account1.setAccountBalance( 1000L );
		service.debit( customer1, account1, 100L );
		
		Iterator<AuditResult> timedActions = timer.getActions();
		assertNotNull( "Timer actions null", timedActions );
		assertTrue( "Timer actions empty", timedActions.hasNext() );
		
		AuditResult audit = timedActions.next();
		assertEquals( "Action name mismatch", "debit", audit.getActionName() );
		assertSame( "User mismatch", customer1, audit.getUser() );
		assertTrue( "Expected success", audit.wasSuccess() );
		assertNull( "Expected no error", audit.getThrown() );
		assertTrue( "Start time should be > 0", audit.getStart() > 0 );
		assertTrue( "Duration should be >= 0", audit.getDuration() >= 0 );
		
		assertFalse( "Too many timer actions", timedActions.hasNext() );
	}
	
	@Test
	public void testDebitWithToAccount() {
		Account account1 = customer1.getAccount( "account1" );
		Account account3 = customer2.getAccount( "account3" );
		account1.setAccountBalance( 1000L );
		service.debit( customer1, account1, account3, 100L );
		
		Iterator<AuditResult> timedActions = timer.getActions();
		assertNotNull( "Timer actions null", timedActions );
		assertTrue( "Timer actions empty", timedActions.hasNext() );
		
		AuditResult audit = timedActions.next();
		assertEquals( "Action name mismatch", "debit", audit.getActionName() );
		assertSame( "User mismatch", customer1, audit.getUser() );
		assertTrue( "Expected success", audit.wasSuccess() );
		assertNull( "Expected no error", audit.getThrown() );
		assertTrue( "Start time should be > 0", audit.getStart() > 0 );
		assertTrue( "Duration should be >= 0", audit.getDuration() >= 0 );
		
		assertFalse( "Too many timer actions", timedActions.hasNext() );
	}
	
	@Test
	public void testDebitFails() {
		Account account1 = customer1.getAccount( "account1" );
		service.debit( customer1, account1, 100L );
		
		Iterator<AuditResult> timedActions = timer.getActions();
		assertNotNull( "Timer actions null", timedActions );
		assertTrue( "Timer actions empty", timedActions.hasNext() );
		
		AuditResult audit = timedActions.next();
		assertEquals( "Action name mismatch", "debit", audit.getActionName() );
		assertSame( "User mismatch", customer1, audit.getUser() );
		assertFalse( "Expected failure", audit.wasSuccess() );
		assertNull( "Expected no error", audit.getThrown() );
		assertTrue( "Start time should be > 0", audit.getStart() > 0 );
		assertTrue( "Duration should be >= 0", audit.getDuration() >= 0 );
		
		assertFalse( "Too many timer actions", timedActions.hasNext() );
	}
	
	@Test
	public void testDebitCrashes() {
		Account account1 = customer1.getAccount( "account1" );
		Throwable error = null;
		
		try {
			service.debit( customer1, account1, -100 );
		}
		catch ( Throwable t ) {
			error = t;
		}
		
		Iterator<AuditResult> timedActions = timer.getActions();
		assertNotNull( "Timer actions null", timedActions );
		assertTrue( "Timer actions empty", timedActions.hasNext() );
		
		AuditResult audit = timedActions.next();
		assertEquals( "Action name mismatch", "debit", audit.getActionName() );
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
		Account account1 = customer1.getAccount( "account1" );
		service.credit( customer1, account1, 100L );
		
		Iterator<AuditResult> timedActions = timer.getActions();
		assertNotNull( "Timer actions null", timedActions );
		assertTrue( "Timer actions empty", timedActions.hasNext() );
		
		AuditResult audit = timedActions.next();
		assertEquals( "Action name mismatch", "credit", audit.getActionName() );
		assertSame( "User mismatch", customer1, audit.getUser() );
		assertTrue( "Expected success", audit.wasSuccess() );
		assertNull( "Expected no error", audit.getThrown() );
		assertTrue( "Start time should be > 0", audit.getStart() > 0 );
		assertTrue( "Duration should be >= 0", audit.getDuration() >= 0 );
		
		assertFalse( "Too many timer actions", timedActions.hasNext() );
	}
	
	@Test
	public void testCreditWithToAccount() {
		Account account1 = customer1.getAccount( "account1" );
		Account account3 = customer2.getAccount("account3");
		service.credit( customer1, account1, account3, 100L );
		
		Iterator<AuditResult> timedActions = timer.getActions();
		assertNotNull( "Timer actions null", timedActions );
		assertTrue( "Timer actions empty", timedActions.hasNext() );
		
		AuditResult audit = timedActions.next();
		assertEquals( "Action name mismatch", "credit", audit.getActionName() );
		assertSame( "User mismatch", customer1, audit.getUser() );
		assertTrue( "Expected success", audit.wasSuccess() );
		assertNull( "Expected no error", audit.getThrown() );
		assertTrue( "Start time should be > 0", audit.getStart() > 0 );
		assertTrue( "Duration should be >= 0", audit.getDuration() >= 0 );
		
		assertFalse( "Too many timer actions", timedActions.hasNext() );
	}
	
	@Test
	public void testCreditFails() {
		Account account1 = customer1.getAccount( "account1" );
		service.credit( customer1, account1, Long.MAX_VALUE );
		
		Iterator<AuditResult> timedActions = timer.getActions();
		assertNotNull( "Timer actions null", timedActions );
		assertTrue( "Timer actions empty", timedActions.hasNext() );
		
		AuditResult audit = timedActions.next();
		assertEquals( "Action name mismatch", "credit", audit.getActionName() );
		assertSame( "User mismatch", customer1, audit.getUser() );
		assertFalse( "Expected failure", audit.wasSuccess() );
		assertNull( "Expected no error", audit.getThrown() );
		assertTrue( "Start time should be > 0", audit.getStart() > 0 );
		assertTrue( "Duration should be >= 0", audit.getDuration() >= 0 );
		
		assertFalse( "Too many timer actions", timedActions.hasNext() );
	}
	
	@Test
	public void testCreditCrashes() {
		Account account1 = customer1.getAccount( "account1" );
		Throwable error = null;
		
		try {
			service.credit( customer1, account1, -100 );
		}
		catch ( Throwable t ) {
			error = t;
		}
		
		Iterator<AuditResult> timedActions = timer.getActions();
		assertNotNull( "Timer actions null", timedActions );
		assertTrue( "Timer actions empty", timedActions.hasNext() );
		
		AuditResult audit = timedActions.next();
		assertEquals( "Action name mismatch", "credit", audit.getActionName() );
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
		Account account1 = customer1.getAccount( "account1" );
		service.debit( customer1, account1, 100L );
		service.credit( customer1, account1, 1000L );
		service.debit( customer1, account1, 100L );
		
		Iterator<AuditResult> timedActions = timer.getActions();
		assertNotNull( "Timer actions null", timedActions );
		
		assertTrue( "Timer actions empty", timedActions.hasNext() );
		assertEquals( "Action name mismatch", "debit", timedActions.next().getActionName() );
		
		assertTrue( "Too few timer actions", timedActions.hasNext() );
		assertEquals( "Action name mismatch", "credit", timedActions.next().getActionName() );
		
		assertTrue( "Too few timer actions", timedActions.hasNext() );
		assertEquals( "Action name mismatch", "debit", timedActions.next().getActionName() );
		
		assertFalse( "Too many timer actions", timedActions.hasNext() );
	}
}
