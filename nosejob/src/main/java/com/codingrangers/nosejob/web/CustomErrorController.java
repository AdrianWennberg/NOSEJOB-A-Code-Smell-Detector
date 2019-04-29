/* NOSEJOB by The Coding Rangers
 * William Akinsanya
 * Alessandro Baccin
 * Peter Major
 * Adrian Wennberg
 * For the UCD module:
 * 	 Software Engineering Project 3
 */
 package com.codingrangers.nosejob.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

	@RequestMapping("/error")
	public String handleError(HttpServletRequest request, Model model) {
		if (!model.containsAttribute("errorMessage")) {
			model.addAttribute("errorMessage", "Oops, something went wrong!");
		}

		if (!model.containsAttribute("tip")) {
			model.addAttribute("tip", "Please return home and try again.");
		}

		return "error";
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}
}
