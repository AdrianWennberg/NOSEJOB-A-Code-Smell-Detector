package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.models.*;
import com.codingrangers.nosejob.reports.GlobalReport;

import java.util.ArrayList;
import java.util.List;

public abstract class SnifferBase implements IProjectAnalyser{

    protected List<ISniffer> analysers;
    private IProjectData projectToAnalyze;

    public SnifferBase() {
        analysers = new ArrayList<>();
    }

    @Override
    public void setProjectToAnalyse(IProjectData projectData) {
        projectToAnalyze = projectData;
    }

    @Override
    public IProjectReport getProjectReport() {
        IProjectReport result = new GlobalReport();

        for(ISniffer analyser: analysers) {
            analyser.setProjectToAnalyse(projectToAnalyze);
            ((GlobalReport) result).addSmellReportToProjectReport(analyser.getSmellReport());
        }

        return result;
    }
}

