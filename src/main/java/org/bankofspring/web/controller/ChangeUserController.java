package org.bankofspring.web.controller;

import javax.servlet.http.HttpSession;

import org.bankofspring.dao.UserDAO;
import org.bankofspring.web.interceptor.LoggedInInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/selectUser")
public class ChangeUserController {

	@Autowired
	private UserDAO userDao;
	
	@RequestMapping(method = RequestMethod.GET)
	public String selectUser(ModelMap map) {
		map.addAttribute("users", userDao.getAllUsers() );
		return "changeUser";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String selectUser(@RequestParam("user") String username, HttpSession session ) {
		session.setAttribute(LoggedInInterceptor.USER_SESSION_ATTRIBUTE_KEY, username);
		return "redirect:/home";
	}
}
