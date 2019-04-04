package com.codingrangers.nosejob.models;

import java.util.List;

public interface IProjectReport {
	float getProjectScore();

	List<String> getSmellNames();

	List<ISmellReport> getSmellReports();

	ISmellReport getReportForSpecifiedSmell(String smellName);
}
