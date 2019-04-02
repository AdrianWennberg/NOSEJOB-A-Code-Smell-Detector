package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.helpers.DataStructureHelpers;
import com.codingrangers.nosejob.models.*;

import java.util.ArrayList;
import java.util.List;

public class PrimitiveObsession implements CodeSniffer, SmellReport {
    private static final String NAME = "Primitive Obsession";
    private List<SmellInstance> instancesOfPrimObsWithinProj;

    private class ClassAnalyzer implements SmellInstance {
        private ClassData currentClassAnalysed;
        private List<SmellInstance> instancesOfPrimObsWithinClass;

        private class MethodAnalyzer implements SmellInstance {
            private MethodData methodToAnalyze;

            private MethodAnalyzer(MethodData myMethod) {
                this.methodToAnalyze = myMethod;
            }

            public int countSeverityInMethodsParametersTypes() {
                return methodToAnalyze.getPrimitiveParameterCount();
            }

            public int countSeverityInMethodsLocalTypes() {
                return methodToAnalyze.getPrimitiveLocalCount();
            }

            public int countSeverityInMethodsReturnTypes() {
                return (methodToAnalyze.hasPrimitiveReturnType()) ? 1 : 0;
            }

            public CodeData getLocation() {
                return methodToAnalyze;
            }

            public int getSmellSeverity() {
                return countSeverityInMethodsParametersTypes() +
                        countSeverityInMethodsLocalTypes() +
                        countSeverityInMethodsReturnTypes();
            }
        }

        private ClassAnalyzer(ClassData myClass) {
            this.currentClassAnalysed = myClass;
            instancesOfPrimObsWithinClass = new ArrayList<>();
            if (measureSeverityInClassFieldsTypes() > 0) instancesOfPrimObsWithinClass.add(this);
            retrieveSmellInstancesFromClassMethods();
        }

        public int measureSeverityInClassFieldsTypes() {
            ArrayList<VariableData> fields = new ArrayList<>();

            for (String fieldName : currentClassAnalysed.getFieldsNames()) {
                fields.add(currentClassAnalysed.getField(fieldName));
            }

            return DataStructureHelpers.countPrimitives(fields);
        }

        public void retrieveSmellInstancesFromClassMethods() {
            for (String methodSignature : currentClassAnalysed.getMethodSignatures()) {
                SmellInstance methodToAnalyze = new MethodAnalyzer(currentClassAnalysed.getMethod(methodSignature));
                if (methodToAnalyze.getSmellSeverity() > 0) instancesOfPrimObsWithinClass.add(methodToAnalyze);
            }
        }

        public CodeData getLocation() {
            return currentClassAnalysed;
        }

        public int getSmellSeverity() {
            int severity = 0;
            for (SmellInstance instance : instancesOfPrimObsWithinClass) {
                severity += instance.getSmellSeverity();
            }
            return severity;
        }
    }

    public PrimitiveObsession() {
        instancesOfPrimObsWithinProj = new ArrayList<>();
    }

    public SmellReport analyzeCode(ProjectData data) {
        return null;
    }

    public String getSmellName() {
        return NAME;
    }

    public int getTotalSmellSeverity() {
        return 0;
    }

    public List<SmellInstance> getSmellInstances() {
        return null;
    }
}
