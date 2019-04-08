package com.codingrangers.nosejob.models;

public interface Sniffer {
	void setProjectToAnalyse(ProjectData codeData);

	SmellReportBody getSmellReport();
}
