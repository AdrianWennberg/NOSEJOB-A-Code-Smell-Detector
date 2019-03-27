package com.codingrangers.nosejob.web;

import com.codingrangers.nosejob.parser.ProjectParser;
import com.codingrangers.nosejob.models.ProjectData;
import com.codingrangers.nosejob.storage.StorageProperties;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProjectAnalyserController {

	@Autowired
	private final ProjectParser projectParser;

	private ProjectData projectData;
	private final Path rootLocation;

	@Autowired
	public ProjectAnalyserController(ProjectParser parser, StorageProperties properties) {
		this.projectParser = parser;
		this.rootLocation = Paths.get(properties.getLocation());
	}

	@GetMapping("/analyse")
	public String analyseProject() {
		this.projectData = this.projectParser.parseProject(this.rootLocation.toString());
		return "dashboard";
	}

}
