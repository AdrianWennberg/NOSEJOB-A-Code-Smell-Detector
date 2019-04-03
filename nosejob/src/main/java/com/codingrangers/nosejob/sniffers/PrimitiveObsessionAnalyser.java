package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.helpers.DataStructureHelpers;
import com.codingrangers.nosejob.models.*;

import java.util.ArrayList;
import java.util.List;

public class PrimitiveObsessionAnalyser implements IAnalyser {
    private static final String NAME = "Primitive Obsession";
    private IClassData currentClassAnalysed;

    private class MethodDiagnosis implements ISmell {
        private IMethodData currentMethodToAnalyze;

        private MethodDiagnosis() { }

        public int countSeverityInMethodsParametersTypes() {
            return currentMethodToAnalyze.getPrimitiveParameterCount();
        }

        public int countSeverityInMethodsLocalTypes() {
            return currentMethodToAnalyze.getPrimitiveLocalCount();
        }

        public int countSeverityInMethodsReturnTypes() {
            return (currentMethodToAnalyze.hasPrimitiveReturnType()) ? 1 : 0;
        }

        public void setCodeData(ICodeData codeData) {
            currentMethodToAnalyze = (IMethodData) codeData;
        }

        public ICodeData getLocation() {
            return currentMethodToAnalyze;
        }

        public int getSmellSeverity() {
            return countSeverityInMethodsParametersTypes() +
                    countSeverityInMethodsLocalTypes() +
                    countSeverityInMethodsReturnTypes();
        }
    }

    private class FieldsDiagnosis implements ISmell {
        private IClassData currentClassToAnalyze;

        private FieldsDiagnosis() { }

        public int measureSeverityInClassFieldsTypes() {
            ArrayList<IVariableData> fields = new ArrayList<>();

            for (String fieldName : currentClassAnalysed.getFieldsNames()) {
                fields.add(currentClassAnalysed.getField(fieldName));
            }

            return DataStructureHelpers.countPrimitives(fields);
        }
        public void setCodeData(ICodeData codeData) {
            currentClassToAnalyze = (IClassData) codeData;
        }

        public ICodeData getLocation() {
            return currentClassAnalysed;
        }

        public int getSmellSeverity() {
            return measureSeverityInClassFieldsTypes();
        }
    }

    public PrimitiveObsessionAnalyser() {}

    public List<ISmell> retrieveSmellsFromClassMethods() {
        List<ISmell> methodsSmells = new ArrayList<>();

        for (String methodSignature : currentClassAnalysed.getMethodSignatures()) {
            ISmell methodDiagnosis = new MethodDiagnosis();
            methodDiagnosis.setCodeData(currentClassAnalysed.getMethod(methodSignature));
            if (methodDiagnosis.getSmellSeverity() > 0) methodsSmells.add(methodDiagnosis);
        }

        return methodsSmells;
    }

    public void setCodeData(ICodeData codeData) {
        this.currentClassAnalysed = (IClassData) codeData;
    }

    public String getSmellName() {
        return NAME;
    }

    public int getTotalSmellSeverity() {
        int totalSeverity = 0;

        for(ISmell smells : getSmellInstances()){
            totalSeverity += smells.getSmellSeverity();
        }

        return totalSeverity;
    }

    public List<ISmell> getSmellInstances() {
        List<ISmell> result = new ArrayList<>();

        ISmell fieldsDiagnosis = new FieldsDiagnosis();
        fieldsDiagnosis.setCodeData(currentClassAnalysed);
        if (fieldsDiagnosis.getSmellSeverity() > 0) result.add(fieldsDiagnosis);

        result.addAll(retrieveSmellsFromClassMethods());

        return result;
    }
}
