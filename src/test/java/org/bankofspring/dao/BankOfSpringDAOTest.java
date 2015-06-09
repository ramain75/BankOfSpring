package org.bankofspring.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.bankofspring.model.Account;
import org.bankofspring.model.AccountTransaction;
import org.bankofspring.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:BankOfSpring-ds-test.xml")
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class BankOfSpringDAOTest {

	@Autowired
	BankOfSpringDAO dao;

	@Test
	public void testGetSingleCustomer() {
		Customer cust1 = dao.getCustomer("1");
		assertEquals ("customer id unexpected value " , "1", cust1.getCustomerID());
		assertEquals ("customer name unexpected value " , "customer one", cust1.getName());
	}
	
	@Test
	public void testCustomersByAccount() {
		List<Customer> customers = dao.getCustomersForAccount("account4");
		assertEquals("unexpected number of customers for account4",2,customers.size());
		Customer cust1 = customers.get(0);
		assertNotNull("Customer 1 should not be null", cust1);
		assertEquals ("cust id unexpected value " , "1", cust1.getCustomerID());
		assertEquals ("cust id unexpected value " , "customer one", cust1.getName());
		Customer cust2 = customers.get(1);
		assertNotNull("Customer 2 should not be null", cust2);
		assertEquals ("cust id unexpected value " , "2", cust2.getCustomerID());
		assertEquals ("cust id unexpected value " , "customer two", cust2.getName());
		
		customers = dao.getCustomersForAccount("account1");
		assertEquals("unexpected number of customers for account1",1,customers.size());
		 cust1 = customers.get(0);
		assertNotNull("Customer 1 should not be null", cust1);
		assertEquals ("cust id unexpected value " , "1", cust1.getCustomerID());
		assertEquals ("cust id unexpected value " , "customer one", cust1.getName());
	}
	@Test
	public void testGetCustomerList() {
		List <Customer> listCust = dao.getCustomers();
		assertEquals("customer list has unexpected size ", 2, listCust.size());
		Customer cust1 = listCust.get(0);
		assertNotNull("Customer 1 should not be null", cust1);
		assertEquals ("cust id unexpected value " , "1", cust1.getCustomerID());
		assertEquals ("cust id unexpected value " , "customer one", cust1.getName());
		Customer cust2 = listCust.get(1);
		assertNotNull("Customer 2 should not be null", cust2);
		assertEquals ("cust id unexpected value " , "2", cust2.getCustomerID());
		assertEquals ("cust id unexpected value " , "customer two", cust2.getName());
	}
	@Test
	public void getAccount() {
		Account account = dao.getAccount("account3");
		assertNotNull(account);
		assertEquals ("incorrect account number", "account3",account.getAccountNumber());
		assertEquals ("incorrect account description", "account3description",account.getAccountDescription());
		assertEquals ("incorrect balance for account " + account.getAccountNumber(), 100L, account.getAccountBalance());
		//assertEquals ("incorrect max balance for account " + account.getAccountNumber(), 99999999999L, account.getMaxBalanceAmount());
//		assertNotNull(account.getOwningCustomers());
//		assertEquals(1,account.getOwningCustomers().size());
//		Customer customer = account.getOwningCustomers().get(0);
//		assertNotNull(customer);
//		assertEquals("2",customer.getCustomerID());
	}
	
	public void testGetAccountTransaction() {
		
	}
	public void testCreateTransaction() {
		Account account1 = dao.getAccount("account1");
		Account account2 = dao.getAccount("account2");
		assertNotNull(account1);
		assertNotNull(account2);
		
		AccountTransaction txn = new AccountTransaction(account1, account2, 100L);
		dao.createAccountTransaction(txn);
	}
	
	
}
