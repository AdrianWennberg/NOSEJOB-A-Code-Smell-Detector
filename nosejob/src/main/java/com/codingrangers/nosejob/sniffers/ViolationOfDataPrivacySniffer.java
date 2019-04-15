package com.codingrangers.nosejob.sniffers;
import com.codingrangers.nosejob.models.*;
import com.codingrangers.nosejob.reports.SmellReport;

public class ViolationOfDataPrivacySniffer extends GeneralSniffer {
    private static final String NAME = "Bloated Code";

    private class FieldsDiagnosis implements Smell {
        private ClassData currentClassToAnalyze;

        private FieldsDiagnosis() {
        }

        public float measureSeverityInClassFieldsTypes() {
            if (currentClassToAnalyze.getFieldsNames().size() == 0)
                return 0f;

            return currentClassToAnalyze.countPublicFields() / currentClassToAnalyze.countFields();
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

    public void retrieveSmellFromFields(ClassData currentClassAnalysed) {
        if (currentClassAnalysed.equals(null))
            throw new NullPointerException("Cannot analyse methods of a null.");

        if (currentClassAnalysed.getFieldsNames().size() > 0) {
            Smell fieldsDiagnosis = new FieldsDiagnosis();
            fieldsDiagnosis.setCodeData(currentClassAnalysed);
            smells.add(fieldsDiagnosis);
        }
    }

    public void retrieveSmellsFromClasses() {
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
