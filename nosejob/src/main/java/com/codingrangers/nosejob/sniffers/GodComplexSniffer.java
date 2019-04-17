package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.models.ClassData;
import com.codingrangers.nosejob.models.CodeData;
import com.codingrangers.nosejob.models.Smell;
import com.codingrangers.nosejob.models.SmellReportBody;
import com.codingrangers.nosejob.reports.SmellReport;

public class GodComplexSniffer extends GeneralSniffer {
    private static final String NAME = "God Complex";

    private class ClassDiagnosis implements Smell {
        private ClassData currentClassToAnalyse;

        private float howMuchItsUsedRatio() {
            String currentClassName = currentClassToAnalyse.getName();
            float callsRatio = 0f;

            for(String className : currentProjectToAnalyse.getClassNames()){
                if(!className.equals(currentClassName)) {
                   ClassData callingClass = currentProjectToAnalyse.getClassData(className);
                   callsRatio += callingClass.getMethodCallsTo(currentClassName).size();
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
            severity = (howMuchItsUsedRatio() > 2f) ? (severity - 2f) : 0f;
            return severity;
        }
    }

    public void retrieveSmellFromSingularClass(ClassData currentClassToAnalyse) {
        if (currentClassToAnalyse.equals(null))
            throw new NullPointerException("Cannot analyse methods of a null.");

        if (currentClassToAnalyse.getFieldsNames().size() > 0) {
            Smell classDiagnosis = new ClassDiagnosis();
            classDiagnosis.setCodeData(currentClassToAnalyse);
            smells.add(classDiagnosis);
        }
    }

    public void retrieveSmellsFromClasses() {
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
