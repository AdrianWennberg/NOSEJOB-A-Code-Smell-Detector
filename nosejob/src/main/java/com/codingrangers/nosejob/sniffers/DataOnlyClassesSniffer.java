package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.models.SmellReportBody;
import com.codingrangers.nosejob.reports.SmellReport;

public class DataOnlyClassesSnifferTest extends GeneralSniffer {
    private static final String NAME = "Data Only Classes";

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
