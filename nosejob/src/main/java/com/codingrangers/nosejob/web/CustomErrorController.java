package com.codingrangers.nosejob.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

	@RequestMapping("/error")
	public String handleError(HttpServletRequest request, Model model) {
		model.addAttribute("errorMessage", "Oops, something went wrong!");
		return "error";
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}
