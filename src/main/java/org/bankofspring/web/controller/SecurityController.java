package org.bankofspring.web.controller;

import javax.servlet.http.HttpSession;

import org.bankofspring.web.interceptor.LoggedInInterceptor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute(LoggedInInterceptor.USER_SESSION_ATTRIBUTE_KEY);
		return "redirect:/home";
	}
}
