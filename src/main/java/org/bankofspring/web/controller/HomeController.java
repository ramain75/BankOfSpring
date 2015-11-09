package org.bankofspring.web.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	public static final String USER_SESSION_ATTRIBUTE_KEY = "bos_current_user";
	/**
	 * Mapping for the home page.
	 * @return the template.
	 */
	@RequestMapping({"/home","/"})
	public String home( HttpSession session, Principal principal) {
		String username = principal.getName();
		session.setAttribute(USER_SESSION_ATTRIBUTE_KEY, username);
		return "home";
	}
}
