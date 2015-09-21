package org.bankofspring.controllers;

import java.util.List;
import java.util.Map;

import org.bankofspring.dao.AccountDAO;
import org.bankofspring.model.Account;
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
	@RequestMapping (value="/",method=RequestMethod.GET)
	public String showTransfer(@ModelAttribute("customerForm") final BasicTransferForm basicTransferForm, Map<String,Object> model) {
			List <Account> list = accountDao.getAccountsForCustomer(1);
			model.put("allAccounts", list);
			model.put("basicTransferForm", basicTransferForm);
			return "basictransfer";
	}
}
