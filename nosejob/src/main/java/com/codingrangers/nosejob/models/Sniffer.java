package com.codingrangers.nosejob.models;

public interface Sniffer {
	void setProjectToSniff(ProjectData codeData);

	SmellReportBody getSmellReport();
}
