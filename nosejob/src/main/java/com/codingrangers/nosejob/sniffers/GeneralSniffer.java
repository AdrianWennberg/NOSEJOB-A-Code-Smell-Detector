package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.models.ProjectData;
import com.codingrangers.nosejob.models.Smell;
import com.codingrangers.nosejob.models.SmellReportBody;
import com.codingrangers.nosejob.models.Sniffer;
import com.codingrangers.nosejob.reports.SmellReport;

import java.util.ArrayList;
import java.util.List;

public abstract class GeneralSniffer implements Sniffer {
    protected ProjectData currentProjectToAnalyse;
    protected List<Smell> smells;

    public GeneralSniffer() {
        this.smells = new ArrayList<>();
    }

    @Override
    public void setProjectToSniff(ProjectData projectData) {
        this.currentProjectToAnalyse = projectData;
    }

    public static class InappropriateIntimacySnifferTest extends GeneralSniffer {
        private static final String NAME = "Inappropriate Intimacy";

        @Override
        public SmellReportBody getSmellReport() {
            /**
             * TODO
             * retrieveSmellsFromClasses();*/

            SmellReport report = new SmellReport();
            report.setSmellName(NAME);
            report.addSmells(smells);
            return report;
        }
    }
}
