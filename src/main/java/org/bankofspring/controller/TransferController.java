package org.bankofspring.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.bankofspring.dao.AccountDAO;
import org.bankofspring.model.Account;
import org.bankofspring.model.TransferDetails;
import org.bankofspring.service.BankOfSpringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for the accounts page
 */
@Controller
public class TransferController {
	
	private static String NA = "N/A";
	
	@Autowired
	@Qualifier("bankService")
	private BankOfSpringService service;
	
	@Autowired
	@Qualifier("hibernateAccountDao")
	private AccountDAO accountDAO;

	@RequestMapping(value = "/transfer", method = RequestMethod.GET)
	public ModelAndView transfer(HttpServletResponse response) throws IOException{
		
		//Create the model and view object to send back
		ModelAndView modelandview = new ModelAndView("transfer", "command", new TransferDetails());
		
		//Get the accounts
		List<Account> accounts = accountDAO.getAccounts();
		
		//Create map of accounts
		Map<String, String> accountMap = new HashMap<String, String>();
		accountMap.put(NA, "Select Account...");
		
		for(Account account : accounts){
			accountMap.put(account.getAccountNumber(), account.getAccountDescription()+": ["+account.getAccountNumber()+"]");
		}
		
		
		//Add the necessary objects that we need in the view
		modelandview.addObject("accounts", accountMap);
				
		return modelandview;
	}
	
	@RequestMapping(value = "/transferAmount", method = RequestMethod.POST)
	public String transferAmount(@ModelAttribute("TransferDetails")TransferDetails transferDetails, 
			ModelMap model) {
		
		return "result";
	}
	
	
}
