package com.codingrangers.nosejob.models;

import java.util.List;

public interface ProjectReport {
	float getProjectScore();

	List<String> getSmellNames();

	List<SmellReportBody> getSmellReports();

	SmellReportBody getReportForSpecifiedSmell(String smellName);
}
