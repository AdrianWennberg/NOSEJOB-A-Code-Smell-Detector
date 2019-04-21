package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.models.*;
import com.codingrangers.nosejob.reports.SmellReport;
import java.util.List;

public class InappropriateIntimacySniffer extends GeneralSniffer {
    private static final String NAME = "Inappropriate Intimacy";

    private class ClassDiagnosis implements Smell {
        private ClassData currentClassToAnalyse;

        private float countHowManyExternalFieldsAreUsed() {
            int externalFieldsCounter = 0;

            for(String className : currentProjectToAnalyse.getClassNames()){
                if(!className.equals(currentClassToAnalyse.getName())) {
                    ClassData classToCompareWith = currentProjectToAnalyse.getClassData(className);
                    List<FieldReference> usedFields = currentClassToAnalyse.getFieldReferencesTo(classToCompareWith.getFullyQualifiedName());
                    for(FieldReference fieldReference : usedFields){
                        if(!fieldReference.isInternal()) externalFieldsCounter++;
                    }
                }
            }

            /**callsRatio /= currentProjectToAnalyse.getClassNames().size() - 1;*/
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
            return (getSmellSeverity() > 0) ? true : false;
        }

        @Override
        public float getSmellSeverity() {
            return countHowManyExternalFieldsAreUsed();
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
