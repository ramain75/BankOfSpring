package org.bankofspring.dao;

import static org.junit.Assert.*;

import java.util.List;

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
	public void testGetCustomers() {
		List <Customer> listCust = dao.getCustomers();
		assertEquals("customer list has unexpected size ", 2, listCust.size());
		Customer cust1 = listCust.get(0);
		assertNotNull("Customer 1 should not be null", cust1);
		assertEquals ("cust id unexpected value " , "1", cust1.getCustomerID());
		assertEquals ("cust id unexpected value " , "Customer1", cust1.getName());

	}
	
	
}
