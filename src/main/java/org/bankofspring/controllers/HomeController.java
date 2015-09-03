package org.bankofspring.controllers;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class HomeController {

	
	public HomeController () {
		
	}
	
	@RequestMapping ( { "/", "/home"})
	public String showHomePage(Map<String,Object> model) {
		model.put("welcome", "Hello There");
		return "home";
	}
}
