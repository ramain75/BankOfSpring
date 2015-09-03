package org.bankofspring.controllers;

import java.util.List;
import java.util.Map;

import org.bankofspring.dao.CustomerDAO;
import org.bankofspring.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/customers")
public class CustomerController {
	@Autowired
	CustomerDAO customerDao;
	@RequestMapping ("/")
	public String showCustomerList(Map<String,Object> model) {
		List<Customer> customers = customerDao.getCustomers();
		model.put("customers",customers);
		return "customerslist";
	}
}
