package com.codingrangers.nosejob.sniffers;

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
        smellReports.put(smellReport.getSmellName(), smellReport);
    }

    @Override
    /**TODO How are we gonna calculate the total score?*/
    public float getProjectScore() {
        return 0;
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
