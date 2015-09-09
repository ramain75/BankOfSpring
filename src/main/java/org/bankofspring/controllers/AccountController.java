package org.bankofspring.controllers;

import java.util.List;
import java.util.Map;

import org.bankofspring.dao.AccountDAO;
import org.bankofspring.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/customers/{customerid}/accounts")
public class AccountController {

	@Autowired
	@Qualifier("jdbcAccountDao")
	AccountDAO accountDao;
	@RequestMapping (value="/",method=RequestMethod.GET)
	public String showAccountListForCustomer(@PathVariable("customerid") int customerId, Model model) {
		List<Account> accounts = accountDao.getAccountsForCustomer(customerId);
		model.addAttribute("accounts",accounts);
		return "accountslist";
	}
}
