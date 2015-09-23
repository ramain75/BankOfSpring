package org.bankofspring.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.bankofspring.dao.AccountDAO;
import org.bankofspring.model.Account;
import org.bankofspring.web.AccountForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
		model.addAttribute("customerid", customerId);
		model.addAttribute("accounts",accounts);
		return "accountslist";
	}
	@RequestMapping (value="/new", method=RequestMethod.GET)
	public String newAccount( @PathVariable("customerid") int customerId, @ModelAttribute("accountForm") final AccountForm accountForm, Map<String,Object> model) {
		model.put("customerid", customerId);
		model.put("accountForm",accountForm);
		return "newaccount";
	}
	@RequestMapping (value="/addaccount", method=RequestMethod.POST)
	public String addCustomer( @PathVariable("customerid") int customerId, @Valid @ModelAttribute("accountForm")  AccountForm accountForm, final BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "newaccount";
		}
		Account account = new Account();
		account.setAccountNumber(accountForm.getNumber());
		account.setAccountDescription(accountForm.getDescription());
		account.setAccountBalance(accountForm.getBalance());
		account.setMaxBalanceAmount(accountForm.getMaxbalance());
		account.setCustomerId(customerId);
		accountDao.addNewAccount(account);
		return "redirect:/customers/" + customerId + "/accounts/";
	}
	@RequestMapping (value="/{accountNumber}",method=RequestMethod.GET)
	public String getAccount(@PathVariable("customerid") int customerId, @PathVariable("accountNumber") String accountNumber, Map<String,Object> model) {
		Account account = accountDao.getAccountByNumber(accountNumber);
		model.put("account",account);
		model.put("customerid", customerId);
		return "editaccount";
	}
	@RequestMapping (value="/{accountNumber}/save",method=RequestMethod.POST)
	public String saveAccountr(@PathVariable("customerid") int customerId,  @PathVariable("accountNumber") String accountNumber, @ModelAttribute("account") Account account, final BindingResult result) {
		if (result.hasErrors()) {
			return "editaccount";
		}
		accountDao.updateAccount(account);
		return "redirect:/customers/" + customerId + "/accounts/";
	}
}
