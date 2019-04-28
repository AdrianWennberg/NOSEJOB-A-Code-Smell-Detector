package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.models.*;
import com.codingrangers.nosejob.reports.SmellReport;

public class InappropriateIntimacySniffer extends GeneralSniffer {
    private static final String NAME = "Inappropriate Intimacy";

    private class ClassDiagnosis implements Smell {
        private ClassData currentClassToAnalyse;

        private float countHowManyExternalFieldsAreUsed() {
            int externalFieldsCounter = 0;

            for(String className : currentProjectToAnalyse.getClassNames()){
                for (FieldReference reference : currentClassToAnalyse.getFieldReferencesTo(className)) {
                    if (!reference.isInternal()) {
                        externalFieldsCounter += 1;
                        System.out.printf("Reference from: %s to %s.%s%n",
                                currentClassToAnalyse.getFullyQualifiedName(),
                                className,
                                reference.getReferredFieldName());
                    }
                }
            }

            return externalFieldsCounter;
        }

        @Override
        public void setCodeData(CodeData codeData) {
            currentClassToAnalyse = (ClassData) codeData;
        }

        @Override
        public CodeData getLocation() {
            return currentClassToAnalyse;
        }

        @Override
        public boolean isSmelly() {
            return getSmellSeverity() > 0;
        }

        @Override
        public float getSmellSeverity() {

            float severity;
            if (countHowManyExternalFieldsAreUsed() > 0f) {
                System.out.printf("Class name: %s Fields Used: %s%n",
                        currentClassToAnalyse.getFullyQualifiedName(),
                        countHowManyExternalFieldsAreUsed());
                severity = 1f;
            } else severity = 0f;
            return severity;
        }
    }

    private void retrieveSmellFromSingularClass(ClassData currentClassToAnalyse) {
        if (currentClassToAnalyse == null)
            throw new NullPointerException("Cannot analyse a null class.");

        Smell classDiagnosis = new ClassDiagnosis();
        classDiagnosis.setCodeData(currentClassToAnalyse);
        smells.add(classDiagnosis);
    }

    private void retrieveSmellsFromClasses() {
        if (currentProjectToAnalyse == null)
            throw new NullPointerException("Cannot analyse a null project.");

        for(String className : currentProjectToAnalyse.getClassNames()){
            ClassData currentClassToAnalyse = currentProjectToAnalyse.getClassData(className);
            retrieveSmellFromSingularClass(currentClassToAnalyse);
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
