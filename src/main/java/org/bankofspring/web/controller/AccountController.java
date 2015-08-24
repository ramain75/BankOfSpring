package org.bankofspring.web.controller;

import java.util.List;

import org.bankofspring.dao.AccountDAO;
import org.bankofspring.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/account")
public class AccountController {
	
	@Autowired
	@Qualifier("jdbcAccountDao")
	private AccountDAO accountDAO;
	
	@RequestMapping(value = "/{number}", method = RequestMethod.GET)
	public String getAccountDetails(@PathVariable("number") String number, ModelMap map) {
		map.addAttribute("account", accountDAO.getAccountByNumber(number));
		return "accountDetails";
	}

}
