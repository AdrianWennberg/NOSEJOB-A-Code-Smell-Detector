package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.models.*;
import com.codingrangers.nosejob.reports.GlobalReport;

import java.util.ArrayList;
import java.util.List;

public abstract class SnifferBase implements ProjectAnalyser {

	protected List<Sniffer> analysers;
	private ProjectData projectToAnalyze;

	public SnifferBase() {
		analysers = new ArrayList<>();
	}

	@Override
	public void setProjectToAnalyse(ProjectData projectData) {
		projectToAnalyze = projectData;
	}

	@Override
	public ProjectReport getProjectReport() {
		ProjectReport result = new GlobalReport();

		for (Sniffer analyser : analysers) {
			analyser.setProjectToAnalyse(projectToAnalyze);
			((GlobalReport) result).addSmellReportToProjectReport(analyser.getSmellReport());
		}

		return result;
	}
}
