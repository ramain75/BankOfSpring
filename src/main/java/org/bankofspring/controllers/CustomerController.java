package org.bankofspring.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.bankofspring.dao.CustomerDAO;
import org.bankofspring.model.Customer;
import org.bankofspring.web.CustomerForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@Controller
@RequestMapping("/customers")
public class CustomerController {
	@Autowired
	CustomerDAO customerDao;
	@RequestMapping (value="/",method=RequestMethod.GET)
	public String showCustomerList(Map<String,Object> model) {
		List<Customer> customers = customerDao.getCustomers();
		model.put("customers",customers);
		return "customerslist";
	}
	@RequestMapping (value="/new", method=RequestMethod.GET)
	public String newCustomer( @ModelAttribute("customerForm") final CustomerForm customerForm, Map<String,Object> model) {
		model.put("customerForm",customerForm);
		return "newcustomer";
	}
	@RequestMapping (value="/addcustomer", method=RequestMethod.POST)
	public String addCustomer(@Valid @ModelAttribute("customerForm")  CustomerForm customerForm, final BindingResult result) {
		if (result.hasErrors()) {
			return "newcustomer";
		}
		customerDao.addCustomer(customerForm.getName(), customerForm.getEmail(), customerForm.getDescription());
		return "redirect:/customers/";
	}
}
