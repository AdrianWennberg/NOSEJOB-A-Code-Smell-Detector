package com.codingrangers.nosejob.models;

import java.util.List;

public interface IProjectReport {
	int getProjectScore();

	List<String> getSmellNames();

	IAnalyser getSmellReport(String smellName);
}
