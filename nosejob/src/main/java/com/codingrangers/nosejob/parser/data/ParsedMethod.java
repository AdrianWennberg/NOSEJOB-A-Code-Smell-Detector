package com.codingrangers.nosejob.parser.data;

import com.codingrangers.nosejob.models.MethodData;
import com.codingrangers.nosejob.models.VariableData;

import java.util.ArrayList;
import java.util.List;

/**
 * ParsedMethod TODO: Need to unit test this
 */
public class ParsedMethod extends ParsedCodeUnit implements MethodData, Cloneable {

    private VariableData returnType;
    private final String className;
    private final List<VariableData> parameters;
    private final List<VariableData> localVariables;

    ParsedMethod(String blockNamePrefix, String blockName, String filePath, String className) {
        super(blockNamePrefix, blockName, filePath);
        this.className = className;
        parameters = new ArrayList<>();
        localVariables = new ArrayList<>();
    }

    void addReturnType(VariableData methodReturnType) {
        returnType = methodReturnType;
    }

    void addParameter(VariableData newParameter) {
        parameters.add(newParameter);
    }

    void addVariable(VariableData newVariable) {
        localVariables.add(newVariable);
    }

    @Override
    public String getClassName() {
        return className;
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

    public ParsedMethodReference addReferenceToMethod(String fullyQualifiedClassName, String methodSignature) {
        ParsedMethodReference methodRef = new ParsedMethodReference(getFilePath(), getFullyQualifiedName(), fullyQualifiedClassName, methodSignature);
        ReferenceStorage.get().add(methodRef);
        return methodRef;
    }

    public ParsedFieldReference addReferenceToField(String fullyQualifiedClassName, String fieldName) {
        ParsedFieldReference fieldRef = new ParsedFieldReference(getFilePath(), getFullyQualifiedName(), fullyQualifiedClassName, fieldName);
        ReferenceStorage.get().add(fieldRef);
        return fieldRef;
    }

    @Override
    protected final ParsedMethod clone() {
        try {
            return (ParsedMethod) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException();
        }
    }
}
