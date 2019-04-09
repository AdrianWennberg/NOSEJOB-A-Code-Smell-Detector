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
		if (projectToAnalyze.equals(null))
			throw new NullPointerException("Cannot analyse a null project.");

		ProjectReport result = new GlobalReport();

		for (Sniffer analyser : analysers) {
			analyser.setProjectToSniff(projectToAnalyze);
			((GlobalReport) result).addSmellReportToProjectReport(analyser.getSmellReport());
		}

		return result;
	}
}
