package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.models.SmellReportBody;
import com.codingrangers.nosejob.reports.SmellReport;

public class GodComplexSniffer extends GeneralSniffer {
    private static final String NAME = "God Complex";

    @Override
    public SmellReportBody getSmellReport() {
        /**TODO
         * retrieveSmellsFromClasses();*/

        SmellReport report = new SmellReport();
        report.setSmellName(NAME);
        report.addSmells(smells);
        return report;
    }
}
