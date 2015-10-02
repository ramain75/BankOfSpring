package org.bankofspring.dao;

import org.bankofspring.model.Customer;

public interface CustomerDAO {
	Customer getCustomerById(Integer id);
}
