package com.codingrangers.nosejob.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.beans.factory.annotation.Value;

@Controller
public class HomeController {

	@Value("${spring.application.name}")
	String appName;

	@RequestMapping("/")
	public String homePage(Model model) {
		model.addAttribute("appName", appName);
		return "home";
	}
}
