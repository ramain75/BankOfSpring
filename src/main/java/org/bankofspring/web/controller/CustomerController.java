package org.bankofspring.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.bankofspring.dao.AccountDAO;
import org.bankofspring.dao.CustomerDAO;
import org.bankofspring.model.Account;
import org.bankofspring.web.interceptor.LoggedInInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerDAO customerDAO;
	
	@Autowired
	@Qualifier("jdbcAccountDao")
	private AccountDAO accountDAO;
	
	@RequestMapping(method = RequestMethod.GET)
	public String selectCustomer(HttpSession session, ModelMap map) {
		map.addAttribute("customers", customerDAO.getCustomersForUser((String) session.getAttribute(LoggedInInterceptor.USER_SESSION_ATTRIBUTE_KEY)));
		return "showCustomers";
	}
	
	@RequestMapping( value = "/{id}", method = RequestMethod.GET)
	public String showAccounts(@PathVariable("id") Integer customer, ModelMap map) {
		List<Account> accounts = accountDAO.getCustomerAccounts(customer);
		map.addAttribute("accounts", accounts);
		map.addAttribute("id", customer);
		return "accountList";
	}
}
