package org.bankofspring.db;

import static org.junit.Assert.*;

import org.bankofspring.model.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:BankOfSpring.xml", "classpath:BankOfSpring-ds-test.xml", "classpath:BankOfSpring-dao.xml"})
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class CustomerDAOTest {

	@Autowired
	private CustomerDAO dao;
	
	@Test
	public void testGetById() throws Exception {
		Customer customer = dao.getCustomerById("1");
		assertNotNull("Expected customer 1 to be loaded", customer);
		assertEquals("Customer Id not as expected", "1", customer.getCustomerID());
		assertEquals("Customer Name not as expected", "customer one", customer.getName());
		assertEquals("Customer Username not as expected", "user1", customer.getUsername());
		assertEquals("Customer Password not as expected", "test", customer.getPassword());
	}
}
