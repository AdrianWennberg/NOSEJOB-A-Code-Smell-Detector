/* NOSEJOB by The Coding Rangers
 * William Akinsanya
 * Alessandro Baccin
 * Peter Major
 * Adrian Wennberg
 * For the UCD module:
 * 	 Software Engineering Project 3
 */
 package com.codingrangers.nosejob.web;

import com.codingrangers.nosejob.models.ProjectData;
import com.codingrangers.nosejob.models.ProjectReport;
import com.codingrangers.nosejob.models.StorageService;
import com.codingrangers.nosejob.parser.ProjectParser;
import com.codingrangers.nosejob.sniffers.ProjectSniffer;
import com.codingrangers.nosejob.storage.StorageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class DashboardController {

	private final ProjectParser projectParser;

	private final StorageService storageService;

	private final StorageProperties storageProperties;

	@Autowired
	public DashboardController(ProjectParser parser, StorageProperties properties, StorageService storageService) {
		this.projectParser = parser;
		this.storageProperties = properties;
		this.storageService = storageService;
	}

	@GetMapping("/dashboard")
	public String analyseProject(Model model) {
		try {
			Path rootLocation = Paths.get(this.storageProperties.getLocation());
			ProjectData projectData = this.projectParser.parseProject(rootLocation.toString());
			ProjectSniffer globalSniffer = new ProjectSniffer();
			globalSniffer.setProjectToAnalyse(projectData);
			ProjectReport projectReport = globalSniffer.getProjectReport();
			model.addAttribute("projectScore", "Project smelliness: " + (int) (projectReport.getProjectScore() * 100) + "/100");
			model.addAttribute("smellReports", projectReport.getSmellReports());
		} catch (Exception e) {
			return "redirect:/error";
		} finally {
			this.storageService.deleteAll();
		}
		return "dashboard";
	}

}
