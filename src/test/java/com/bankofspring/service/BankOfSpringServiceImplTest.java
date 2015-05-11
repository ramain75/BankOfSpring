package com.bankofspring.service;

import static org.junit.Assert.*;

import javax.inject.Inject;
import javax.inject.Named;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bankofspring.model.Account;
import com.bankofspring.model.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:BankOfSpringApp.xml")

public class BankOfSpringServiceImplTest {

	
	/**
	 * Using JSR 330 for the first bean to see if it works, Spring annotations for the rest
	 * Note - this is probably a bad idea in general as it's not very consistent, but proves that they work together
	 */
	@Inject
	@Named("customerBean1")
	Customer customer1;
	
	@Autowired
	@Qualifier("customerBean2")
	Customer customer2;
	
	@Autowired
	@Qualifier("customerBean3")
	Customer customer3;
	
	@Autowired
	@Qualifier("account1")
	Account account1;
	
	@Autowired
	@Qualifier("account2")
	Account account2;
	
	@Autowired
	BankOfSpringService service;
	
	
	@Test
	public void testCustomer1BeanWiring() {
		
		/**
		 * Retrieving beans via the application context for now as we haven't covered autowiring
		 * properly in the book yet.
		 **/	
		assertNotNull("Customer 1 Has been Populated", customer1);
		assertEquals("Expected Customer Username","TestCustomer1",customer1.getUsername());
		assertEquals("Expected Customer Password","testpass",customer1.getPassword());
		assertEquals("Expected Customer Name","Test Customer",customer1.getName());
		assertEquals("Expected Customer ID","123456789",customer1.getCustomerID() );
	}
	
	@Test
	public void testCustomer2BeanWiring() {
		
		/**
		 * Retrieving beans via the application context for now as we haven't covered autowiring
		 * properly in the book yet.
		 **/
		assertNotNull("Customer 2 Has been Populated", customer2);
		assertEquals("Expected Customer Username","TestCustomer2",customer2.getUsername());
		assertEquals("Expected Customer Password","testpass",customer2.getPassword());
		assertEquals("Expected Customer Name","Test Customer2",customer2.getName());
		assertEquals("Expected Customer ID","123456799",customer2.getCustomerID() );
	}
	
	@Test
	public void testAccount1BeanWiring() {	
		assertNotNull("Account1 Has been Populated", account1);
		assertEquals("Expected Customer Username","123456789",account1.getAccountNumber());
		assertEquals("Account balance should be empty", new Double(0.00), account1.getAccountAmount());
		assertNotNull("Account 1 should have customer", account1.getOwningCustomers());
		assertEquals("Account 1 Should have 2 customers", 2, account1.getOwningCustomers().size());
		assertEquals("Customer 1 is the first owning customer","TestCustomer1", account1.getOwningCustomers().get(0).getUsername());
		
	}
	
	@Test
	public void testService(){
				
		//Credit some money in
		service.credit(customer1, account1, 10000.00);
		
		//Check that the account1 has been credited the money
		assertEquals("Money has been credited", new Double(10000.00), account1.getAccountAmount());
		
		//Check that the credit has been logged
		assertEquals("Transaction has been logged", 1, service.getTransactionLog().getTransactions().size());
		assertEquals("Transaction has been logged", account1, service.getTransactionLog().getTransactions().get(0).getToAccount());
		assertEquals("Transaction has been logged", new Double(10000.00), service.getTransactionLog().getTransactions().get(0).getTransactionAmount());
		
		//Debit some money from account 1 and credit to account 2
		service.debit(customer1, account1, account2, 5000.00);
		
		//Check that the money has been dbited
		assertEquals("Money has been debited", new Double(5000.00), account1.getAccountAmount());
		assertEquals("Money has been credited", new Double(5000.00), account2.getAccountAmount());
		
		//Check that the debit has been logged
		assertEquals("Transaction has been logged", 2, service.getTransactionLog().getTransactions().size());
		assertEquals("Transaction has been logged", account1, service.getTransactionLog().getTransactions().get(1).getFromAccount());
		assertEquals("Transaction has been logged", account2, service.getTransactionLog().getTransactions().get(1).getToAccount());
		assertEquals("Transaction has been logged", new Double(-5000.00), service.getTransactionLog().getTransactions().get(1).getTransactionAmount());
		
		//Customer 3 takes out some cash
		service.debit(customer3, account2, null, 2000.00);
		assertEquals("Money has been debited", new Double(3000.00), account2.getAccountAmount());
		
		//Check that the debit has been logged
		assertEquals("Transaction has been logged", 3, service.getTransactionLog().getTransactions().size());
		assertEquals("Transaction has been logged", account2, service.getTransactionLog().getTransactions().get(2).getFromAccount());
		assertEquals("Transaction has been logged", new Double(-2000.00), service.getTransactionLog().getTransactions().get(2).getTransactionAmount());
		
		
		
	}

}
