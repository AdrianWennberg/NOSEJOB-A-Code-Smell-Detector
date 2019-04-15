package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.models.ClassData;
import com.codingrangers.nosejob.models.Smell;
import com.codingrangers.nosejob.models.SmellReportBody;
import com.codingrangers.nosejob.reports.SmellReport;

public class FeatureEnvySniffer extends GeneralSniffer {
    private static final String NAME = "Feature Envy";

    public void retrieveSmellFromSingularClass(ClassData currentClassAnalysed) {
        if (currentClassAnalysed.equals(null))
            throw new NullPointerException("Cannot analyse methods of a null.");

        if (currentClassAnalysed.getFieldsNames().size() > 0) {
            /** TODO
             * Smell fieldsDiagnosis = new ViolationOfDataPrivacySniffer.FieldsDiagnosis();
            fieldsDiagnosis.setCodeData(currentClassAnalysed);
            smells.add(fieldsDiagnosis);*/
        }
    }

    public void retrieveSmellsFromClasses() {
        if (currentProjectToAnalyse.equals(null))
            throw new NullPointerException("Cannot analyse a null project.");

        for(String className : currentProjectToAnalyse.getClassNames()){
            ClassData currentClassToAnalyse = currentProjectToAnalyse.getClassData(className);
            /**TODO
             * retrieveSmellFromFields(currentClassToAnalyse);*/
        }
    }

    @Override
    public SmellReportBody getSmellReport() {
        retrieveSmellsFromClasses();

        SmellReport report = new SmellReport();
        report.setSmellName(NAME);
        report.addSmells(smells);
        return report;
    }
}
