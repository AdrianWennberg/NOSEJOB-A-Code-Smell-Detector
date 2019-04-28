package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.models.ClassData;
import com.codingrangers.nosejob.models.CodeData;
import com.codingrangers.nosejob.models.Smell;
import com.codingrangers.nosejob.models.SmellReportBody;
import com.codingrangers.nosejob.reports.SmellReport;

public class ViolationOfDataPrivacySniffer extends GeneralSniffer {
    private static final String NAME = "Violation Of Data Privacy";

    private class FieldsDiagnosis implements Smell {
        private ClassData currentClassToAnalyze;

        private float measureSeverityInClassFieldsTypes() {
            if (currentClassToAnalyze.countFields() - currentClassToAnalyze.countStaticFields() == 0)
                return 0f;

            return (float) (currentClassToAnalyze.countPublicFields() - currentClassToAnalyze.countStaticPublicFields())
                    / (1.0f * currentClassToAnalyze.countFields() - currentClassToAnalyze.countStaticFields());
        }

        @Override
        public void setCodeData(CodeData codeData) {
            currentClassToAnalyze = (ClassData) codeData;
        }

        @Override
        public CodeData getLocation() {
            return currentClassToAnalyze;
        }

        @Override
        public boolean isSmelly() {
            return getSmellSeverity() > 0;
        }

        @Override
        public float getSmellSeverity() {
            return measureSeverityInClassFieldsTypes();
        }
    }

    private void retrieveSmellFromFields(ClassData currentClassToAnalyse) {
        if (currentClassToAnalyse == null)
            throw new NullPointerException("Cannot analyse fields of a null.");

        if (currentClassToAnalyse.countFields() > 0) {
            Smell fieldsDiagnosis = new FieldsDiagnosis();
            fieldsDiagnosis.setCodeData(currentClassToAnalyse);
            smells.add(fieldsDiagnosis);
        }
    }

    private void retrieveSmellsFromClasses() {
        if (currentProjectToAnalyse == null)
            throw new NullPointerException("Cannot analyse a null project.");


        for(String className : currentProjectToAnalyse.getClassNames()){
            ClassData currentClassToAnalyse = currentProjectToAnalyse.getClassData(className);
            retrieveSmellFromFields(currentClassToAnalyse);
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