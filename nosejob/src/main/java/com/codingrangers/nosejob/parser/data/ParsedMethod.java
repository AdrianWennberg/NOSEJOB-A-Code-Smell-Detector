package com.codingrangers.nosejob.parser.data;

import com.codingrangers.nosejob.models.MethodData;
import com.codingrangers.nosejob.models.VariableData;

import java.util.ArrayList;
import java.util.List;

public class ParsedMethod extends ParsedCodeUnit implements MethodData, Cloneable {

    private VariableData returnType;
    private final List<VariableData> parameters;
    private final List<VariableData> localVariables;
    private ParsedVariable variablePrototype;
    private ReferenceStorage referenceStorage;

    ParsedMethod(String blockNamePrefix, String blockName, String filePath) {
        super(blockNamePrefix, blockName, filePath);
        parameters = new ArrayList<>();
        localVariables = new ArrayList<>();
        variablePrototype = new ParsedVariable("", "", "");
        setReturnType("void", true);
    }

    public void setReturnType(String variableType, boolean isPrimitive) {
        ParsedVariable temp = new ParsedVariable(getFullyQualifiedName(), getName(), getFilePath());
        temp.setVariableType(variableType);
        if (isPrimitive)
            temp.setIsPrimitive();
        returnType = temp;
    }

    void setReturnType(VariableData returnType) {
        this.returnType = returnType;
    }

    void setVariablePrototype(ParsedVariable newPrototype) {
        variablePrototype = newPrototype;
    }

    public ParsedVariable createParameter(String name) {
        ParsedVariable variable = variablePrototype.clone();
        variable.setName(name);
        variable.setNamePrefix(getFullyQualifiedName());
        variable.setFilePath(getFilePath());
        parameters.add(variable);
        return variable;
    }


    public ParsedVariable createVariable(String name) {
        ParsedVariable variable = variablePrototype.clone();
        variable.setName(name);
        variable.setNamePrefix(getFullyQualifiedName());
        variable.setFilePath(getFilePath());
        localVariables.add(variable);
        return variable;
    }

    @Override
    public String getClassName() {
        return getNamePrefix();
    }

    @Override
    public VariableData getReturnType() {
        return returnType;
    }

    @Override
    public boolean hasPrimitiveReturnType() {
        return returnType.isPrimitive();
    }

    @Override
    public List<VariableData> getParameters() {
        return new ArrayList<>(parameters);
    }

    @Override
    public List<VariableData> getLocalVariables() {
        return new ArrayList<>(localVariables);
    }

    @Override
    public int getPrimitiveParameterCount() {
        return DataStructureHelpers.countPrimitives(parameters);
    }

    @Override
    public int getPrimitiveLocalCount() {
        return DataStructureHelpers.countPrimitives(localVariables);
    }

    void setReferenceStorage(ReferenceStorage storage) {
        referenceStorage = storage;
    }

    public ParsedMethodReference addReferenceToMethod(String fullyQualifiedClassName, String methodSignature) {
        ParsedMethodReference methodRef = new ParsedMethodReference(getFilePath(), getClassName(), fullyQualifiedClassName, methodSignature);
        referenceStorage.add(methodRef);
        return methodRef;
    }

    public ParsedFieldReference addReferenceToField(String fullyQualifiedClassName, String fieldName) {
        ParsedFieldReference fieldRef = new ParsedFieldReference(getFilePath(), getClassName(), fullyQualifiedClassName, fieldName);
        referenceStorage.add(fieldRef);
        return fieldRef;
    }

    @Override
    protected ParsedMethod clone() {
        return new ParsedMethod(getNamePrefix(), getName(), getFilePath());
    }
}
