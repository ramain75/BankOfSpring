package org.bankofspring.dao.jdbc;

import static org.junit.Assert.*;

import java.util.List;

import org.bankofspring.dao.CustomerDAO;
import org.bankofspring.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:BankOfSpring-ds-test.xml", "classpath:BankOfSpring.xml"})
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class CustomerDAOJDBCTest {

	@Autowired
	private CustomerDAO customerDAO;
	
	@Test
	public void testGetCustomerById() throws Exception {
		Customer customer = customerDAO.getCustomerById(1);
		assertEquals((Integer) 1, customer.getId());
		assertEquals("customer one", customer.getName());
		assertEquals("cust1@gmail.com", customer.getEmail());
		assertEquals("happy customer", customer.getDescription());
	}
	@Test
	public void testGetCustomers() throws Exception {
		List<Customer> list = customerDAO.getCustomers();
		assertEquals("invalid number of customers",2,list.size() );
		
	}
	@Test
	public void testAddCustomer() throws Exception {
		Customer newCust = customerDAO.addCustomer("newcustomer", "newcust@hotmail.com", "description");
		assertNotNull(newCust);
		assertEquals("newcustomer",newCust.getName());
		assertEquals("newcust@hotmail.com",newCust.getEmail());
		assertEquals("description",newCust.getDescription());
	}
	@Test
	public void testUpdateCustomer() throws Exception {
		Customer customer = customerDAO.getCustomerById(1);
		assertEquals((Integer) 1, customer.getId());
		assertEquals("customer one", customer.getName());
		assertEquals("cust1@gmail.com", customer.getEmail());
		assertEquals("happy customer", customer.getDescription());
		customer.setDescription("changed customer");
		customer.setName("customer one changed");
		customer.setEmail("email@changed.com");
		assertTrue(customerDAO.updateCustomer(customer));
		customer = customerDAO.getCustomerById(1);
		assertEquals((Integer) 1, customer.getId());
		assertEquals("customer one changed", customer.getName());
		assertEquals("email@changed.com", customer.getEmail());
		assertEquals("changed customer", customer.getDescription());
	}
	
}
