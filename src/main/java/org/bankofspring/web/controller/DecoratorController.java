package org.bankofspring.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DecoratorController {

	@RequestMapping("/decorator")
	public String getDecorator() {
		return "decorator/template";
	}
}
