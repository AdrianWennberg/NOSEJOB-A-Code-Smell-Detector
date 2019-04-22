package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.models.*;
import com.codingrangers.nosejob.reports.SmellReport;

public class FeatureEnvySniffer extends GeneralSniffer {
    private static final String NAME = "Feature Envy";

    private class ClassDiagnosis implements Smell {
        private ClassData currentClassToAnalyse;

        private float howManyExternalMethodsItsUsingratio() {
            float callsRatio = 0f;

            for(String className : currentProjectToAnalyse.getClassNames()){
                if(!className.equals(currentClassToAnalyse.getName())) {
                    ClassData classToCompareWith = currentProjectToAnalyse.getClassData(className);
                    callsRatio += currentClassToAnalyse.getMethodCallsTo(classToCompareWith.getFullyQualifiedName()).size() / currentClassToAnalyse.getInternalMethodCalls().size();
                }
            }

            callsRatio /= currentProjectToAnalyse.getClassNames().size() - 1;

            return callsRatio;
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
            float severity = 0f;
            severity = (howManyExternalMethodsItsUsingratio() > 1f) ? (severity - 1f) : 0f;
            return severity;
        }
    }

    private void retrieveSmellFromSingularClass(ClassData currentClassToAnalyse) {
        if (currentClassToAnalyse.equals(null))
            throw new NullPointerException("Cannot analyse methods of a null.");

        if (currentClassToAnalyse.getFieldsNames().size() > 0) {
            Smell classDiagnosis = new ClassDiagnosis();
            classDiagnosis.setCodeData(currentClassToAnalyse);
            smells.add(classDiagnosis);
        }
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
