package com.codingrangers.nosejob.models.data.parsing;

import java.util.*;

import com.codingrangers.nosejob.models.data.*;

/**
 * ParsedClass TODO: Need to unit test this
 */
public class ParsedClass extends ParsedCodeUnit implements ClassData {

    private Map<String, MethodData> classMethods;
    private Map<String, VariableData> classVariables;

    public ParsedClass(String classNamePrefix, String className, String filePath) {
        super(classNamePrefix, className, filePath);
        classMethods = new HashMap<>();
        classVariables = new HashMap<>();
    }

    public void addMethod(MethodData newMethod) {
        classMethods.put(newMethod.getName(), newMethod);
    }

    public void addVariable(VariableData newVariable) {
        classVariables.put(newVariable.getName(), newVariable);
    }

    @Override
    public List<String> getMethodNames() {
        return new ArrayList<String>(classMethods.keySet());
    }

    @Override
    public MethodData getMethod(String name) {
        return classMethods.get(name);
    }

    @Override
    public List<String> getFieldsNames() {
        return new ArrayList<String>(classVariables.keySet());
    }

    @Override
    public VariableData getField(String name) {
        return classVariables.get(name);
    }
}