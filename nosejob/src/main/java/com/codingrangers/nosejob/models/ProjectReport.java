package com.codingrangers.nosejob.models;

import java.util.List;

public interface ProjectReport {
	int getProjectScore();

	List<String> getSmellNames();

	SmellReport getSmellReport(String smellName);
}
