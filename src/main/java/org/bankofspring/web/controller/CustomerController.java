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
@RequestMapping("/me")
public class CustomerController {

	@Autowired
	private CustomerDAO customerDAO;
	
	@Autowired
	@Qualifier("jdbcAccountDao")
	private AccountDAO accountDAO;
	
	@RequestMapping( method = RequestMethod.GET)
	public String showAccounts(HttpSession session, ModelMap map) {
		String username = (String) session.getAttribute(LoggedInInterceptor.USER_SESSION_ATTRIBUTE_KEY);
		List<Account> accounts = accountDAO.getAccountsForUsername(username);
		map.addAttribute("accounts", accounts);
		return "accountList";
	}
	
	@RequestMapping(value = "/{accountNumber}", method = RequestMethod.GET)
	public String showAccountDetails(@PathVariable("accountNumber") String accountNumber, ModelMap map) {
		map.addAttribute("account", accountDAO.getAccountByNumber(accountNumber));
		map.addAttribute("accountNumber", accountNumber);
		return "accountDetails";
	}
}
