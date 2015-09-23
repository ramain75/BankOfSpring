package org.bankofspring.controllers;

import java.util.List;
import java.util.Map;

import org.bankofspring.dao.AccountDAO;
import org.bankofspring.dao.UserDAO;
import org.bankofspring.model.Account;
import org.bankofspring.model.User;
import org.bankofspring.service.BankOfSpringService;
import org.bankofspring.web.BasicTransferForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/basictransfer")
public class BasicTransferController {
	
	@Autowired
	@Qualifier("jdbcAccountDao")
	AccountDAO accountDao;

	@Autowired
	@Qualifier("jdbcUserDao")
	UserDAO userDao;
	
	@Autowired
	BankOfSpringService service;
	
	@RequestMapping (value="/",method=RequestMethod.GET)
	public String showTransfer(@ModelAttribute("basicTransferForm") final BasicTransferForm basicTransferForm, Map<String,Object> model) {
			List <Account> list = accountDao.getAccountsForCustomer(1);
			model.put("allAccounts", list);
			model.put("basicTransferForm", basicTransferForm);
			return "basictransfer";
	}
	@RequestMapping (value="/do",method=RequestMethod.POST)
	public String doTransfer(@ModelAttribute("basicTransferForm") final BasicTransferForm basicTransferForm, Map<String,Object> model) {
		User user = userDao.getUserByName("user1");
		Account fromAccount = accountDao.getAccountByNumber(basicTransferForm.getFromAccount());
		Account toAccount = accountDao.getAccountByNumber(basicTransferForm.getToAccount());
		long amount = basicTransferForm.getAmount();
		service.transfer(user, fromAccount, toAccount, amount);
		return "redirect:/";
	}
}
