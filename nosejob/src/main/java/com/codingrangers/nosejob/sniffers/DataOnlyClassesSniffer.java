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
                if (className.equals(currentClassToAnalyse.getFullyQualifiedName()))
                    continue;

                calls += currentClassToAnalyse.countMethodCallsTo(className);
            }

            return calls;
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
            int fieldCount = currentClassToAnalyse.countFields() -
                    currentClassToAnalyse.countStaticFields();
            float severity = 1;
            int methodCount = currentClassToAnalyse.countMethods();

            if (countHowManyExternalMethodsItsUsing() >= fieldCount / 2.0 ||
                    currentClassToAnalyse.countInternalMethodCalls() >= fieldCount ||
                    fieldCount == 0)
                return 0f;

            if (methodCount > 2 * fieldCount)
                severity *= 1.0f - (methodCount - 2.0f * fieldCount) / (2.0f * fieldCount);

            severity *= 1.0f - (currentClassToAnalyse.countInternalMethodCalls() * 1.0f / fieldCount);
            severity *= 1.0f - (countHowManyExternalMethodsItsUsing() * 2.0f / fieldCount);

            return severity <= 0 ? 0f : severity;
        }
    }

    private void retrieveSmellFromSingularClass(ClassData currentClassToAnalyse) {
        if (currentClassToAnalyse == null)
            throw new NullPointerException("Cannot analyse methods of a null.");

            Smell classDiagnosis = new ClassDiagnosis();
            classDiagnosis.setCodeData(currentClassToAnalyse);
            smells.add(classDiagnosis);
    }

    private void retrieveSmellsFromClasses() {
        if (currentProjectToAnalyse == null)
            throw new NullPointerException("Cannot analyse a null project.");

        for (String className : currentProjectToAnalyse.getClassNames()) {
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
