package org.bankofspring;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.bankofspring.entity.Account;
import org.bankofspring.entity.AccountTransaction;
import org.bankofspring.entity.User;
import org.bankofspring.exception.OperationDisallowedException;
import org.bankofspring.service.BankService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BankServiceTest {
	private static ApplicationContext context;
	private static BankService service;
	private static Map<String, User> users;
	private static Map<User, List<Account>> userAccounts;
	
	/**
	 * We reload the application context before each test. ClasspathXmlApplicationContext requires obviously
	 * the location of the xml to be known on the classpath
	 */
	@SuppressWarnings("unchecked")
	@Before
	public void setupClass() {
		context = new ClassPathXmlApplicationContext( "BankOfSpringApp.xml" );
		service = (BankService) context.getBean( "bankService" );
		users = (Map<String, User>) context.getBean( "users" );
		userAccounts = (Map<User, List<Account>>) context.getBean( "accounts" );
	}
	
	@Test
	public void testValidTransfer() throws OperationDisallowedException {
		User alice = users.get( "alice" );
		Account aliceCurrent = userAccounts.get( alice ).get( 0 );
		Account bobCurrent = userAccounts.get( users.get( "bob" ) ).get( 0 );
		
		AccountTransaction transaction = service.transfer( alice, aliceCurrent, bobCurrent, 2500 );
		assertEquals( "Unexpected transaction amount", 2500, transaction.getAmount() );
		assertEquals( "Unexpected debit account", aliceCurrent, transaction.getFromAccount() );
		assertEquals( "Unexpected credit account", bobCurrent, transaction.getToAccount() );
		
		assertEquals( "Unexpected debit account balance after transfer", 100000 - 2500, aliceCurrent.getBalance() );
		assertEquals( "Unexpected credit account balance after transfer", 80000 + 2500, bobCurrent.getBalance() );
	}
	
	@Test
	public void testTransferAllMoney() throws OperationDisallowedException {
		User alice = users.get( "alice" );
		Account aliceCurrent = userAccounts.get( alice ).get( 0 );
		Account bobCurrent = userAccounts.get( users.get( "bob" ) ).get( 0 );
		
		AccountTransaction transaction = service.transfer( alice, aliceCurrent, bobCurrent, 100000 );
		assertEquals( "Unexpected transaction amount", 100000, transaction.getAmount() );
		assertEquals( "Unexpected debit account", aliceCurrent, transaction.getFromAccount() );
		assertEquals( "Unexpected credit account", bobCurrent, transaction.getToAccount() );
		
		assertEquals( "Unexpected debit account balance after transfer", 0, aliceCurrent.getBalance() );
		assertEquals( "Unexpected credit account balance after transfer", 180000, bobCurrent.getBalance() );
	}
	
	/**
	 * We do technically allow zero money transfers. Not sure what they'd be useful for.
	 * @throws OperationDisallowedException if failing
	 */
	@Test
	public void testTransferNoMoney() throws OperationDisallowedException {
		User alice = users.get( "alice" );
		Account aliceCurrent = userAccounts.get( alice ).get( 0 );
		Account bobCurrent = userAccounts.get( users.get( "bob" ) ).get( 0 );
		
		AccountTransaction transaction = service.transfer( alice, aliceCurrent, bobCurrent, 0 );
		assertEquals( "Unexpected transaction amount", 0, transaction.getAmount() );
		assertEquals( "Unexpected debit account", aliceCurrent, transaction.getFromAccount() );
		assertEquals( "Unexpected credit account", bobCurrent, transaction.getToAccount() );
		
		assertEquals( "Unexpected debit account balance after transfer", 100000, aliceCurrent.getBalance() );
		assertEquals( "Unexpected credit account balance after transfer", 80000, bobCurrent.getBalance() );
	}
	
	/**
	 * Another weird thing we allow is transfers from and to the same account.
	 * @throws OperationDisallowedException
	 */
	@Test
	public void testTransferToNowhere() throws OperationDisallowedException {
		User alice = users.get( "alice" );
		Account aliceCurrent = userAccounts.get( alice ).get( 0 );
		
		AccountTransaction transaction = service.transfer( alice, aliceCurrent, aliceCurrent, 2500 );
		assertEquals( "Unexpected transaction amount", 2500, transaction.getAmount() );
		assertEquals( "Unexpected debit account", aliceCurrent, transaction.getFromAccount() );
		assertEquals( "Unexpected credit account", aliceCurrent, transaction.getToAccount() );
		
		assertEquals( "Unexpected account balance after transfer", 100000, aliceCurrent.getBalance() );
	}
	
	@Test( expected = OperationDisallowedException.class )
	public void testTransferTooMuchMoney() throws OperationDisallowedException {
		User alice = users.get( "alice" );
		Account aliceCurrent = userAccounts.get( alice ).get( 0 );
		Account bobCurrent = userAccounts.get( users.get( "bob" ) ).get( 0 );
		
		service.transfer( alice, aliceCurrent, bobCurrent, 100001 );
	}
	
	@Test( expected = OperationDisallowedException.class )
	public void testTransferNegativeMoney() throws OperationDisallowedException {
		User alice = users.get( "alice" );
		Account aliceCurrent = userAccounts.get( alice ).get( 0 );
		Account bobCurrent = userAccounts.get( users.get( "bob" ) ).get( 0 );
		
		service.transfer( alice, aliceCurrent, bobCurrent, -1 );
	}
	
	@Test( expected = OperationDisallowedException.class )
	public void testDoesNotOwnAccount() throws OperationDisallowedException {
		User alice = users.get( "alice" );
		Account daveCurrent = userAccounts.get( users.get( "dave" ) ).get( 0 );
		Account bobCurrent = userAccounts.get( users.get( "bob" ) ).get( 0 );
		
		service.transfer( alice, daveCurrent, bobCurrent, 1000 );
	}
	
	@Test( expected = OperationDisallowedException.class )
	public void testNotACustomer() throws OperationDisallowedException {
		User eve = users.get( "eve" );
		Account daveCurrent = userAccounts.get( users.get( "dave" ) ).get( 0 );
		Account bobCurrent = userAccounts.get( users.get( "bob" ) ).get( 0 );
		
		service.transfer( eve, daveCurrent, bobCurrent, 1000 );
	}
	
	@Test( expected = OperationDisallowedException.class )
	public void testNullUser() throws OperationDisallowedException {
		Account daveCurrent = userAccounts.get( users.get( "dave" ) ).get( 0 );
		Account bobCurrent = userAccounts.get( users.get( "bob" ) ).get( 0 );
		
		service.transfer( null, daveCurrent, bobCurrent, 1000 );
	}
	
	@Test( expected = OperationDisallowedException.class )
	public void testNullFromAccount() throws OperationDisallowedException {
		User alice = users.get( "alice" );
		Account bobCurrent = userAccounts.get( users.get( "bob" ) ).get( 0 );
		
		service.transfer( alice, null, bobCurrent, 1000 );
	}
	
	@Test( expected = OperationDisallowedException.class )
	public void testNullToAccount() throws OperationDisallowedException {
		User alice = users.get( "alice" );
		Account aliceCurrent = userAccounts.get( alice ).get( 0 );
		
		service.transfer( alice, aliceCurrent, null, 1000 );
	}
}
