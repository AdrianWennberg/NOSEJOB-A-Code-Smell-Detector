/* NOSEJOB by The Coding Rangers
 * William Akinsanya
 * Alessandro Baccin
 * Peter Major
 * Adrian Wennberg
 * For the UCD module:
 * 	 Software Engineering Project 3
 */
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

        private float calculateHowMuchItsUsedRatio() {
            String currentClassName = currentClassToAnalyse.getName();
            float callsRatio = 0f;

            for(String className : currentProjectToAnalyse.getClassNames()){
                if(!className.equals(currentClassName)) {
                    ClassData callingClass = currentProjectToAnalyse.getClassData(className);
                    callsRatio += callingClass.countMethodCallsTo(currentClassToAnalyse.getFullyQualifiedName());
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
            return getSmellSeverity() > 0;
        }

        @Override
        public float getSmellSeverity() {
            return calculateHowMuchItsUsedRatio() > 6f ? 1f
                    : calculateHowMuchItsUsedRatio() > 5f ? (calculateHowMuchItsUsedRatio() - 5f)
                    : 0f;
        }
    }

    public void retrieveSmellFromSingularClass(ClassData currentClassToAnalyse) {
        if (currentClassToAnalyse.equals(null))
            throw new NullPointerException("Cannot analyse methods of a null.");

        Smell classDiagnosis = new ClassDiagnosis();
        classDiagnosis.setCodeData(currentClassToAnalyse);
        if(classDiagnosis.isSmelly()) {
            smells.add(classDiagnosis);
        }
    }

    public void retrieveSmellsFromClasses() {
        if (currentProjectToAnalyse.equals(null))
            throw new NullPointerException("Cannot analyse a null project.");

        if(currentProjectToAnalyse.getClassNames().size() > 2 ) {
            for (String className : currentProjectToAnalyse.getClassNames()) {
                ClassData currentClassToAnalyse = currentProjectToAnalyse.getClassData(className);
                retrieveSmellFromSingularClass(currentClassToAnalyse);
            }
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