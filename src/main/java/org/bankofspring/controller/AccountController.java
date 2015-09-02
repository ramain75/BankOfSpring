package org.bankofspring.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.bankofspring.service.BankOfSpringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountController {
	protected static final int MAX_COUNT = 75;
	
	@Autowired
	BankOfSpringService service;
	
	@RequestMapping("/account")
	public ModelAndView showAccounts( HttpServletResponse response ) throws IOException {
		ModelAndView accountsModelAndView = new ModelAndView();
		accountsModelAndView.setViewName( "accountList" );
		accountsModelAndView.addObject( "accounts", this.service.listAccounts() );
		return accountsModelAndView;
	}
}
