package org.bankofspring.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.bankofspring.model.Account;
import org.bankofspring.model.User;
import org.bankofspring.service.BankOfSpringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@Autowired
	@Qualifier( "bankService" )
	private BankOfSpringService service;

	@RequestMapping({"/", "/home*"})
	public ModelAndView test(HttpServletResponse response) throws IOException{
		return new ModelAndView("home");
	}

	@RequestMapping( { "/listAccounts" } )
	public ModelAndView listAccounts( HttpServletResponse response ) throws IOException {

	// TODO: Hardcoded user for now
	User user = new User();
	user.setUsername( "user1" );

	List<Account> accounts = service.getAccounts( user );
	
	ModelAndView mv = new ModelAndView( "list_accounts" );
	mv.getModel().put( "accounts", accounts );
	
		return mv;
	}
}
