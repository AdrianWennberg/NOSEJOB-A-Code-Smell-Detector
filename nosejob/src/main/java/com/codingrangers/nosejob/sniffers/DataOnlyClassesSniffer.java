package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.models.ClassData;
import com.codingrangers.nosejob.models.CodeData;
import com.codingrangers.nosejob.models.Smell;
import com.codingrangers.nosejob.models.SmellReportBody;
import com.codingrangers.nosejob.reports.SmellReport;

public class DataOnlyClassesSniffer extends GeneralSniffer {
    private static final String NAME = "Data Only Classes";

    private class ClassDiagnosis implements Smell {
        private ClassData currentClassToAnalyse;

        private int countHowManyExternalMethodsItsUsing() {
            int calls = 0;

            for(String className : currentProjectToAnalyse.getClassNames()){
                if(!className.equals(currentClassToAnalyse.getName())) {
                    ClassData classToCompareWith = currentProjectToAnalyse.getClassData(className);
                    calls += currentClassToAnalyse.getMethodCallsTo(classToCompareWith.getFullyQualifiedName()).size();
                }
            }

            return calls;
        }

        private int countTotalMethodsDeclaredAndCalled(){
            return countHowManyExternalMethodsItsUsing() + currentClassToAnalyse.getInternalMethodCalls().size() + currentClassToAnalyse.getMethodSignatures().size();
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
            return (countTotalMethodsDeclaredAndCalled() > 3) ? 1 : 0;
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
