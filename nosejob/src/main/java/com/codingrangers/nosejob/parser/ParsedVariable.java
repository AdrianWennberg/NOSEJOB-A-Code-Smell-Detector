package com.codingrangers.nosejob.parser;

import com.codingrangers.nosejob.models.IVariableData;

/**
 * ParsedVariable TODO: Need to unit test this
 */
public class ParsedVariable extends ParsedCodeUnit implements IVariableData {

	private String type;
	private boolean isPrimitive;

	public ParsedVariable(ParsedCodeUnit variableScope, String variableName) {
		super(variableScope.getFullyQualifiedName(), variableName, variableScope.getFilePath());
		isPrimitive = false;
	}

	public void setIsPrimitive() {
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