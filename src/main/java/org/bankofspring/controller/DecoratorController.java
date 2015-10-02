package org.bankofspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for the about page
 */
@Controller
public class DecoratorController {

	@RequestMapping("/decorator")
	public String getDecorator() {
		return "decorator/template";
	}
}