package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.models.*;
import com.codingrangers.nosejob.reports.SmellReport;

public class FeatureEnvySniffer extends GeneralSniffer {
    private static final String NAME = "Feature Envy";

    private class ClassDiagnosis implements Smell {
        private ClassData currentClassToAnalyse;

        private float howManyExternalMethodsItsUsingRatio() {
            float callsRatio = 0f;
            float highestCallRatio = 0f;

            for(String className : currentProjectToAnalyse.getClassNames()){
                if(!className.equals(currentClassToAnalyse.getName())) {
                    ClassData classToCompareWith = currentProjectToAnalyse.getClassData(className);
                    if(currentClassToAnalyse.countInternalMethodCalls() == 0){
                        callsRatio += (float) currentClassToAnalyse.countMethodCallsTo(classToCompareWith.getFullyQualifiedName()) / (currentClassToAnalyse.countInternalMethodCalls() + 1);
                    }
                    else {
                        callsRatio += (float) currentClassToAnalyse.countMethodCallsTo(classToCompareWith.getFullyQualifiedName()) / (currentClassToAnalyse.countInternalMethodCalls());
                    }

                    highestCallRatio = (callsRatio > highestCallRatio) ? callsRatio : highestCallRatio;
                }
            }

            return highestCallRatio;
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
            return (getSmellSeverity() > 0) ? true : false;
        }

        @Override
        public float getSmellSeverity() {
            return howManyExternalMethodsItsUsingRatio() > 2f ? 1f
                    : howManyExternalMethodsItsUsingRatio() > 1f ? (howManyExternalMethodsItsUsingRatio() - 1f)
                    : 0f;
        }
    }

    private void retrieveSmellFromSingularClass(ClassData currentClassToAnalyse) {
        if (currentClassToAnalyse.equals(null))
            throw new NullPointerException("Cannot analyse methods of a null.");

        Smell classDiagnosis = new ClassDiagnosis();
        classDiagnosis.setCodeData(currentClassToAnalyse);
        smells.add(classDiagnosis);
    }

    private void retrieveSmellsFromClasses() {
        if (currentProjectToAnalyse.equals(null))
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