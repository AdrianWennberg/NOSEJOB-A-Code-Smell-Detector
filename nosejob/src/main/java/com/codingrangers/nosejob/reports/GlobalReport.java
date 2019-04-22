package com.codingrangers.nosejob.reports;

import com.codingrangers.nosejob.models.ProjectReport;
import com.codingrangers.nosejob.models.SmellReportBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GlobalReport implements ProjectReport {
	protected Map<String, SmellReportBody> smellReports;

	public GlobalReport() {
		smellReports = new HashMap<>();
	}

	public void addSmellReportToProjectReport(SmellReportBody smellReport) {
		if (smellReport.equals(null)) {
			throw new NullPointerException("Cannot add a null as a SmellReport.");
		}

		smellReports.put(smellReport.getSmellName(), smellReport);
	}

	@Override
	public float getProjectScore() {
		if (getSmellReports().size() == 0)
			return 0f;

		float averageSeverity = 0f;
		for (SmellReportBody report : getSmellReports()) {
			averageSeverity += report.getTotalSmellSeverity();
		}
		averageSeverity /= getSmellReports().size();

		return averageSeverity;
	}

	@Override
	public List<String> getSmellNames() {
		List<String> smellNames = new ArrayList<>();

		for (SmellReportBody report : smellReports.values()) {
			smellNames.add(report.getSmellName());
		}

		return smellNames;
	}

	@Override
	public List<SmellReportBody> getSmellReports() {
		return new ArrayList<SmellReportBody>(smellReports.values());
	}

	@Override
	public SmellReportBody getReportForSpecifiedSmell(String smellName) {
		return smellReports.get(smellName);
	}
}
