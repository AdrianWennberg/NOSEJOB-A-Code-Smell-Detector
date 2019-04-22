package com.codingrangers.nosejob.sniffers;
import com.codingrangers.nosejob.models.*;
import com.codingrangers.nosejob.reports.SmellReport;

public class ViolationOfDataPrivacySniffer extends GeneralSniffer {
    private static final String NAME = "Violation Of Data Privacy";

    private class FieldsDiagnosis implements Smell {
        private ClassData currentClassToAnalyze;

        private float measureSeverityInClassFieldsTypes() {
            if (currentClassToAnalyze.getFieldsNames().size() == 0)
                return 0f;

            return (float) currentClassToAnalyze.countPublicFields() / currentClassToAnalyze.countFields();
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
            return (getSmellSeverity() > 0) ? true : false;
        }

        @Override
        public float getSmellSeverity() {
            return measureSeverityInClassFieldsTypes();
        }
    }

    private void retrieveSmellFromFields(ClassData currentClassToAnalyse) {
        if (currentClassToAnalyse.equals(null))
            throw new NullPointerException("Cannot analyse fields of a null.");

        if (currentClassToAnalyse.getFieldsNames().size() > 0) {
            Smell fieldsDiagnosis = new FieldsDiagnosis();
            fieldsDiagnosis.setCodeData(currentClassToAnalyse);
            smells.add(fieldsDiagnosis);
        }
    }

    private void retrieveSmellsFromClasses() {
        if (currentProjectToAnalyse.equals(null))
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
