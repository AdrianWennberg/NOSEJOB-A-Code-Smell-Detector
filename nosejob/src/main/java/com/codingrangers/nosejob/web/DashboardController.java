package com.codingrangers.nosejob.web;

import com.codingrangers.nosejob.parser.ParseFailedException;
import com.codingrangers.nosejob.parser.ProjectParser;
import com.codingrangers.nosejob.models.ProjectData;
import com.codingrangers.nosejob.storage.StorageProperties;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class DashboardController {

	private final ProjectParser projectParser;

	private final Path rootLocation;

	@Autowired
	public DashboardController(ProjectParser parser, StorageProperties properties) {
		this.projectParser = parser;
		this.rootLocation = Paths.get(properties.getLocation());
	}

	@GetMapping("/dashboard")
	public String analyseProject(Model model) {
		try {
			ProjectData projectData = this.projectParser.parseProject(this.rootLocation.toString());
		}
		catch(FileNotFoundException e) {
			
		}
		// model.addAttribute("projectData", this.projectData);
		return "dashboard";
	}

}
