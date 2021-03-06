/* NOSEJOB by The Coding Rangers
 * William Akinsanya
 * Alessandro Baccin
 * Peter Major
 * Adrian Wennberg
 * For the UCD module:
 * 	 Software Engineering Project 3
 */
 package com.codingrangers.nosejob.models;

import java.util.ArrayList;
import java.util.List;

public interface SmellReportBody {
	void setSmellName(String smellName);

	void addSmell(Smell smell);

	void addSmells(List<Smell> smells);

	String getSmellName();

	List<Smell> getSmells();

	float getTotalSmellSeverity();

	ArrayList<String> getFilesAffectedDatums();

	int getAffectedFilesCount();

	String getDisplayTotalSmellSeverity();
}
