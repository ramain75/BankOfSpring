package org.bankofspring.dao;

import java.util.List;


import org.bankofspring.model.Customer;

public interface CustomerDAO {
	Customer getCustomerById(Integer id);
	List<Customer> getCustomers();
	boolean updateCustomer(Customer customer);
	boolean deleteCustomer (Integer id);
	Customer addCustomer (String name, String email, String description);
}
