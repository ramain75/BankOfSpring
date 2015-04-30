package com.bankofspring.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bankofspring.model.Account;
import com.bankofspring.model.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:BankOfSpringApp.xml")

public class BankOfSpringServiceImplTest {

	
	/**
	 * Autowiring by necessity to get the application context using the SpringJunit4ClassRunner
	 * I am purposely avoiding using autowiring this week for other objects as it's covered in the next chapter
	 */
	@Autowired
    private ApplicationContext applicationContext;
	
	@Test
	public void testCustomer1BeanWiring() {
		
		/**
		 * Retrieving beans via the application context for now as we haven't covered autowiring
		 * properly in the book yet.
		 **/
		Customer customer1 = (Customer) applicationContext.getBean("customerBean1"); 
		
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
		Customer customer1 = (Customer) applicationContext.getBean("customerBean2"); 
		
		assertNotNull("Customer 1 Has been Populated", customer1);
		assertEquals("Expected Customer Username","TestCustomer2",customer1.getUsername());
		assertEquals("Expected Customer Password","testpass",customer1.getPassword());
		assertEquals("Expected Customer Name","Test Customer2",customer1.getName());
		assertEquals("Expected Customer ID","123456799",customer1.getCustomerID() );
	}
	
	@Test
	public void testAccount1BeanWiring() {
		
		Account account1 = (Account) applicationContext.getBean("account1"); 
		
		assertNotNull("Account1 Has been Populated", account1);
		assertEquals("Expected Customer Username","123456789",account1.getAccountNumber());
		assertEquals("Account balance should be empty", new Double(0.00), account1.getAccountAmount());
		assertNotNull("Account 1 should have customer", account1.getOwningCustomers());
		assertEquals("Account 1 Should have 2 customers", 2, account1.getOwningCustomers().size());
		assertEquals("Customer 1 is the first owning customer","TestCustomer1", account1.getOwningCustomers().get(0).getUsername());
		
	}
	
	@Test
	public void testService(){
		
		//Get accounts
		Account account1 = (Account) applicationContext.getBean("account1"); 
		Account account2 = (Account) applicationContext.getBean("account2"); 
		
		//Get User
		Customer customer1 = (Customer) applicationContext.getBean("customerBean1");
		Customer customer3 = (Customer) applicationContext.getBean("customerBean3");
		
		//Get Service
		BankOfSpringService service = (BankOfSpringService) applicationContext.getBean("bankOfSpringService");
		
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
