package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.models.*;

public class BloatedCodeSniffer extends GeneralSniffer {
    private static final String NAME = "Bloated Code";

    private enum Severity {
        LOW (0.16f),
        MEDIUM   (0.33f),
        HIGH   (0.66f),
        VERY_HIGH (1);

        private final float severity;
        Severity(float severity) {
            this.severity = severity;
        }

        public float value(){return severity;}
    }

    private class MethodDiagnosis implements Smell {
        private MethodData currentMethodToAnalyze;

        private MethodDiagnosis() {
        }

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
            float smellSeverity = lineCount > 30 ? Severity.VERY_HIGH.value()
                    : lineCount > 20 ? Severity.HIGH.value()
                    : lineCount > 15 ? Severity.MEDIUM.value()
                    : lineCount > 10 ? Severity.LOW.value()
            : 0f;
           return smellSeverity;
        }
    }

    @Override
    public SmellReportBody getSmellReport() {
        return null;
    }

    @Override
    public void setProjectToAnalyse(ProjectData codeData) {

    }
}
