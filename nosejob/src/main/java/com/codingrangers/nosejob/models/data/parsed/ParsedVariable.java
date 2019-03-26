package com.codingrangers.nosejob.models.data.parsed;

import com.codingrangers.nosejob.models.data.VariableData;

/**
 * ParsedVariable
 * TODO: Need to unit test this
 */
public class ParsedVariable extends ParsedCodeUnit implements VariableData {

    private String type;
    private boolean isPrimitive;

    public ParsedVariable(ParsedCodeUnit variableScope, String variableName, String variableType) {
        super(variableScope.getFullyQualifiedName(), variableName, variableScope.getFilePath());
        isPrimitive = false;
    }

    public void SetIsPrimitive() {
        isPrimitive = true;
    }

    public void setVariableType(String variableType) {
        type = variableType;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public boolean isPrimitive() {
        return isPrimitive;
    }
}