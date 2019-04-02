package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.helpers.DataStructureHelpers;
import com.codingrangers.nosejob.models.*;

import java.util.ArrayList;
import java.util.List;

public class ClassAnalyzer implements ISmellReport {
    private IClassData currentClassAnalysed;
    private List<ISmell> instancesOfPrimObsWithinClass;

    private class MethodAnalyzer implements ISmell {
        private IMethodData methodToAnalyze;

        private MethodAnalyzer(IMethodData myMethod) {
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

        public ICodeData getLocation() {
            return methodToAnalyze;
        }

        public int getSmellSeverity() {
            return countSeverityInMethodsParametersTypes() +
                    countSeverityInMethodsLocalTypes() +
                    countSeverityInMethodsReturnTypes();
        }
    }

    private ClassAnalyzer(IClassData myClass) {
        this.currentClassAnalysed = myClass;
        instancesOfPrimObsWithinClass = new ArrayList<>();
        if (measureSeverityInClassFieldsTypes() > 0) instancesOfPrimObsWithinClass.add(this);
        retrieveSmellInstancesFromClassMethods();
    }

    public int measureSeverityInClassFieldsTypes() {
        ArrayList<IVariableData> fields = new ArrayList<>();

        for (String fieldName : currentClassAnalysed.getFieldsNames()) {
            fields.add(currentClassAnalysed.getField(fieldName));
        }

        return DataStructureHelpers.countPrimitives(fields);
    }

    public void retrieveSmellInstancesFromClassMethods() {
        for (String methodSignature : currentClassAnalysed.getMethodSignatures()) {
            ISmell methodToAnalyze = new MethodAnalyzer(currentClassAnalysed.getMethod(methodSignature));
            if (methodToAnalyze.getSmellSeverity() > 0) instancesOfPrimObsWithinClass.add(methodToAnalyze);
        }
    }

    public String getSmellName() {
        return null;
    }

    public int getTotalSmellSeverity() {
        int totalSeverity = 0;
        instancesOfPrimObsWithinClass.forEach(iSmell -> totalSeverity += iSmell.getSmellSeverity());
        return totalSeverity;
    }

    public List<ISmell> getSmellInstances() {
        return instancesOfPrimObsWithinClass;
    }
}
