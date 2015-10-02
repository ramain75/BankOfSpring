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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for the accounts page
 */
@Controller
public class HomeController {
	
	
	@Autowired
	@Qualifier("hibernateAccountDao")
	private AccountDAO accountDAO;


	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String about(Model model) {	         
        return "home";
    }	     
}
