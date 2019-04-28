package com.codingrangers.nosejob.web;

import com.codingrangers.nosejob.models.ProjectData;
import com.codingrangers.nosejob.models.ProjectReport;
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
			ProjectSniffer globalSniffer = new ProjectSniffer();
			globalSniffer.setProjectToAnalyse(projectData);
			ProjectReport projectReport = globalSniffer.getProjectReport();
			model.addAttribute("projectScore", "Project smelliness: " + (int) (projectReport.getProjectScore() * 100) + "/100");
			model.addAttribute("smellReports", projectReport.getSmellReports());
		} catch (Exception e) {
			return "redirect:/error";
		}
		return "dashboard";
	}

}
