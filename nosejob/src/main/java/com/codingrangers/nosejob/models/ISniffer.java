package com.codingrangers.nosejob.models;

public interface ISniffer {
	void setProjectToAnalyse(IProjectData codeData);

	ISmellReport getSmellReport();
}
