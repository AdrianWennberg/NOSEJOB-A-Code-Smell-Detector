package com.codingrangers.nosejob.reports;

import com.codingrangers.nosejob.models.IProjectReport;
import com.codingrangers.nosejob.models.ISmellReport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GlobalReport implements IProjectReport {
    protected Map<String, ISmellReport> smellReports;

    public GlobalReport(){
        smellReports = new HashMap<>();
    }

    public void addSmellReportToProjectReport(ISmellReport smellReport){
        if(smellReport.equals(null))
            throw new NullPointerException("Cannot add a null as a SmellReport.");

        smellReports.put(smellReport.getSmellName(), smellReport);
    }

    @Override
    public float getProjectScore() {
        if (getSmellReports().size() == 0) return 0f;

        float severity_avg = 0f;
        for(ISmellReport report : getSmellReports()){
            severity_avg += report.getTotalSmellSeverity();
        }
        severity_avg /= getSmellReports().size();

        return severity_avg;
    }

    @Override
    public List<String> getSmellNames() {
        List<String> smellNames = new ArrayList<>();

        for (ISmellReport report : smellReports.values()) {
            smellNames.add(report.getSmellName());
        }

        return smellNames;
    }

    @Override
    public List<ISmellReport> getSmellReports() {
        return ((List<ISmellReport>) smellReports);
    }

    @Override
    public ISmellReport getReportForSpecifiedSmell(String smellName) {
        return smellReports.get(smellName);
    }
}
