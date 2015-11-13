package org.bankofspring.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.bankofspring.dao.AccountDAO;
import org.bankofspring.model.Account;
import org.bankofspring.service.BankOfSpringService;
import org.bankofspring.web.TransferForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("transferForm")
@RequestMapping("/transfer")
public class TransferController {

	@Autowired
	@Qualifier("jdbcAccountDao")
	private AccountDAO accountDao;
	
	@Autowired
	private BankOfSpringService bankOfSpringService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String transferGet(ModelMap map) {
		map.put("transferForm", new TransferForm());
		return "transfer";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String transferPost(ModelMap map, @Valid TransferForm transferForm, Errors errors, HttpSession httpSession) {
		if (errors.hasErrors()) {
			return "transfer";
		}
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		boolean outcome = bankOfSpringService.transfer(username, transferForm);
		map.put("outcome", outcome);
		return "redirect:/transfer/outcome";
	}
	
	@RequestMapping(value = "/outcome", method = RequestMethod.GET)
	public String transferOutcome(ModelMap map, @RequestParam("outcome") String outcome) {
		map.put("outcome", outcome);
		return "transferOutcome";
	}
	
	@ModelAttribute("fromAccounts")
	public List<Account> getFromAccounts(HttpSession session) {
		return accountDao.getAccountsForUsername(SecurityContextHolder.getContext().getAuthentication().getName());
	}
	
	@ModelAttribute("toAccounts")
	public List<Account> getToAccounts() {
		return accountDao.getAllAccounts();
	}
}
