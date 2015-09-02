package org.bankofspring.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.bankofspring.dao.AccountDAO;
import org.bankofspring.dao.CustomerDAO;
import org.bankofspring.model.Account;
import org.bankofspring.model.TransferDetails;
import org.bankofspring.model.User;
import org.bankofspring.service.BankOfSpringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
	
	@Autowired
	@Qualifier("jdbcCustomerDao")
	private CustomerDAO customerDAO;

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
	
	@RequestMapping(value = "/transfer", method = RequestMethod.POST)
	public ModelAndView transferAmount(@ModelAttribute("TransferDetails")TransferDetails transferDetails, 
			ModelMap model) {
		
		System.out.println("From: "+transferDetails.getFromNumber());
		System.out.println("To: "+transferDetails.getToNumber());
		
		//Just get a user for now.... Don't really care which
		User user1 = customerDAO.getCustomerById(1);
		Account fromAccount;
		Account toAccount;
		
		String error = "";
		
		//Get from account
		if(	transferDetails.getFromNumber() != null && transferDetails.getToNumber() != null 
			&& !"N/A".equals(transferDetails.getFromNumber()) && !"N/A".equals(transferDetails.getToNumber())){
			
			try{
				fromAccount = accountDAO.getAccountByNumber(transferDetails.getFromNumber());
				toAccount = accountDAO.getAccountByNumber(transferDetails.getToNumber());
				
				if(!service.transfer(user1, fromAccount, toAccount, (long)transferDetails.getAmount())){
					error = "An error occurred trying to transfer money";
					System.err.println("Error");
				}
			}
			catch(Exception e){
				error = "An error occurred trying to transfer money";
				System.err.println("Error");
			}
		}
		else{
			error = "Account Details not valid";
			System.err.println("Error");
		}
		
		
		ModelAndView mv = new ModelAndView("redirect:transfer");
		mv.addObject("errorString", error);
		
		return mv;
	}
	
	
}
