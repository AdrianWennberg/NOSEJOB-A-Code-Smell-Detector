package com.codingrangers.nosejob.models;

public interface ProjectAnalyser {
	void setProjectToAnalyse(ProjectData projectData);

	ProjectReport getProjectReport();
}
