/* NOSEJOB by The Coding Rangers
 * William Akinsanya
 * Alessandro Baccin
 * Peter Major
 * Adrian Wennberg
 * For the UCD module:
 * 	 Software Engineering Project 3
 */
 package com.codingrangers.nosejob.models;

public interface Sniffer {
	void setProjectToSniff(ProjectData codeData);

	SmellReportBody getSmellReport();
}
