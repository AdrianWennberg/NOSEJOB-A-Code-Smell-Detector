package com.codingrangers.nosejob.models;

import java.util.List;

public interface IProjectReport {
	float getProjectScore();

	List<String> getSmellNames();

	ISmellReport getSmellReport(String smellName);
}
