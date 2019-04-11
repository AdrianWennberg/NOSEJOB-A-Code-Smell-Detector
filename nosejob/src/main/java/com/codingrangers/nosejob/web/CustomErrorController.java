package com.codingrangers.nosejob.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

	@RequestMapping("/error")
	public String handleError(HttpServletRequest request, Model model) {
		String errorMessage;
		if (request.getAuthType().equals(HttpServletRequest.FORM_AUTH)) {
			errorMessage = "You did not upload a file";
		} else {
			errorMessage = "Oops, something went wrong!";
		}
		model.addAttribute("errorMessage", errorMessage);
		return "error";
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}
