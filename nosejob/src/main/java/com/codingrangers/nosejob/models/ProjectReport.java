/* NOSEJOB by The Coding Rangers
 * William Akinsanya
 * Alessandro Baccin
 * Peter Major
 * Adrian Wennberg
 * For the UCD module:
 * 	 Software Engineering Project 3
 */
 package com.codingrangers.nosejob.models;

import java.util.List;

public interface ProjectReport {
	float getProjectScore();

	List<String> getSmellNames();

	List<SmellReportBody> getSmellReports();

	SmellReportBody getReportForSpecifiedSmell(String smellName);
}
