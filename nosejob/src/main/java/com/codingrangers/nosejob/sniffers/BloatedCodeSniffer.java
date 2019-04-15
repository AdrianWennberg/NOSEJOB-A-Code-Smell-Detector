package com.codingrangers.nosejob.sniffers;
import com.codingrangers.nosejob.models.*;
import com.codingrangers.nosejob.reports.SmellReport;

public class BloatedCodeSniffer extends GeneralSniffer {
    private static final String NAME = "Bloated Code";

    private enum Severity {
        VERY_LOW (0.10f),
        LOW (0.20f),
        MEDIUM   (0.50f),
        HIGH   (0.70f),
        VERY_HIGH (1f);

        private final float severity;
        Severity(float severity) {
            this.severity = severity;
        }

        public float value(){return severity;}
    }

    private class MethodDiagnosis implements Smell {
        private MethodData currentMethodToAnalyze;

        @Override
        public void setCodeData(CodeData codeData) {
            currentMethodToAnalyze = (MethodData) codeData;
        }

        @Override
        public CodeData getLocation() {
            return currentMethodToAnalyze;
        }

        @Override
        public boolean isSmelly() {
            return (getSmellSeverity() > 0) ? true : false;
        }

        @Override
        public float getSmellSeverity() {
            int lineCount = currentMethodToAnalyze.getLineCount();
            float smellSeverity = lineCount > 40 ? Severity.VERY_HIGH.value()
                    : lineCount > 30 ? Severity.HIGH.value()
                    : lineCount > 20 ? Severity.MEDIUM.value()
                    : lineCount > 15 ? Severity.LOW.value()
                    : lineCount > 10 ? Severity.VERY_LOW.value()
            : 0f;
           return smellSeverity;
        }
    }

    private void retrieveSmellsFromMethods(ClassData currentClassAnalysed) {
        if (currentClassAnalysed.equals(null))
            throw new NullPointerException("Cannot analyse methods of a null.");

        for (String methodSignature : currentClassAnalysed.getMethodSignatures()) {
            Smell methodDiagnosis = new MethodDiagnosis();
            methodDiagnosis.setCodeData(currentClassAnalysed.getMethod(methodSignature));
            smells.add(methodDiagnosis);
        }
    }

    private void retrieveSmellsFromClasses() {
        if (currentProjectToAnalyse.equals(null))
            throw new NullPointerException("Cannot analyse a null project.");

        for (String className : currentProjectToAnalyse.getClassNames()) {
            ClassData currentClassToAnalyse = currentProjectToAnalyse.getClassData(className);
            retrieveSmellsFromMethods(currentClassToAnalyse);
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
