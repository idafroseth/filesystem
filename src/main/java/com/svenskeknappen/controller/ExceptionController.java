package com.svenskeknappen.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class ExceptionController {

	@RequestMapping(value = "/403")
	public String noAccessException(ModelMap model, Principal principal) {
		model.addAttribute("account", principal);
		return "403";
	}
	

}
