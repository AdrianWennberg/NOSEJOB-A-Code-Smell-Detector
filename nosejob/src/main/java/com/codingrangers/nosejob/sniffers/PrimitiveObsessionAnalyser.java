package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.helpers.DataStructureHelpers;
import com.codingrangers.nosejob.models.*;

import java.util.ArrayList;
import java.util.List;

public class PrimitiveObsessionAnalyser implements IAnalyser, ISmellReport {
    private static final String NAME = "Primitive Obsession";
    private IClassData currentClassAnalysed;

    private class MethodDiagnosis implements ISmell {
        private IMethodData currentMethodToAnalyze;

        private MethodDiagnosis() { }

        public float countSeverityInMethodsParametersTypes() {
            return currentMethodToAnalyze.getPrimitiveParameterCount();
        }

        public int countSeverityInMethodsLocalTypes() {
            return currentMethodToAnalyze.getPrimitiveLocalCount();
        }

        public float countSeverityInMethodsReturnTypes() {
            return (currentMethodToAnalyze.hasPrimitiveReturnType()) ? 1 : 0;
        }

        @Override
        public void setCodeData(ICodeData codeData) {
            currentMethodToAnalyze = (IMethodData) codeData;
        }

        @Override
        public ICodeData getLocation() {
            return currentMethodToAnalyze;
        }

        @Override
        public float getSmellSeverity() {
            return countSeverityInMethodsParametersTypes() +
                    countSeverityInMethodsLocalTypes() +
                    countSeverityInMethodsReturnTypes();
        }
    }

    private class FieldsDiagnosis implements ISmell {
        private IClassData currentClassToAnalyze;

        private FieldsDiagnosis() { }

        public float measureSeverityInClassFieldsTypes() {
            ArrayList<IVariableData> fields = new ArrayList<>();

            for (String fieldName : currentClassAnalysed.getFieldsNames()) {
                fields.add(currentClassAnalysed.getField(fieldName));
            }

            return DataStructureHelpers.countPrimitives(fields);
        }

        @Override
        public void setCodeData(ICodeData codeData) {
            currentClassToAnalyze = (IClassData) codeData;
        }

        @Override
        public ICodeData getLocation() {
            return currentClassAnalysed;
        }

        @Override
        public float getSmellSeverity() {
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

    @Override
    public void setCodeData(ICodeData codeData) {
        this.currentClassAnalysed = (IClassData) codeData;
    }

    @Override
    public String getSmellName() {
        return NAME;
    }

    @Override
    public float getTotalSmellSeverity() {
        int totalSeverity = 0;

        for(ISmell smells : getSmellsWithinProject()){
            totalSeverity += smells.getSmellSeverity();
        }

        return totalSeverity;
    }

    @Override
    public List<ISmell> getSmellsWithinProject() {
        List<ISmell> result = new ArrayList<>();

        ISmell fieldsDiagnosis = new FieldsDiagnosis();
        fieldsDiagnosis.setCodeData(currentClassAnalysed);
        if (fieldsDiagnosis.getSmellSeverity() > 0) result.add(fieldsDiagnosis);

        result.addAll(retrieveSmellsFromClassMethods());

        return result;
    }
}
