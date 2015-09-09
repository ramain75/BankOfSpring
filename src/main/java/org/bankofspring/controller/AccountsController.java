package org.bankofspring.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.bankofspring.dao.AccountDAO;
import org.bankofspring.model.Account;
import org.bankofspring.service.BankOfSpringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for the accounts page
 */
@Controller
public class AccountsController {
	
	@Autowired
	@Qualifier("bankService")
	private BankOfSpringService service;
	
	@Autowired
	@Qualifier("hibernateAccountDao")
	private AccountDAO accountDAO;

	@RequestMapping(value = "/accounts", method = RequestMethod.GET)
	public ModelAndView showAccounts(HttpServletResponse response) throws IOException{
		
		//Create the model and view object to send back
		ModelAndView modelandview = new ModelAndView("accounts");
		
		//Get the accounts
		List<Account> accounts = accountDAO.getAccounts();
		
		//Add the necessary objects that we need in the view
		modelandview.addObject("accounts", accounts);
				
		return modelandview;
	}
}
