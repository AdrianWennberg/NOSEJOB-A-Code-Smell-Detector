package com.codingrangers.nosejob.parser;

import com.codingrangers.nosejob.models.AccessSpecifier;
import com.codingrangers.nosejob.models.VariableData;

/**
 * ParsedVariable TODO: Need to unit test this
 */
public class ParsedVariable extends ParsedCodeUnit implements VariableData {

	private String type;
	private boolean isPrimitive;

	public ParsedVariable(ParsedCodeUnit variableScope, String variableName) {
		super(variableScope.getFullyQualifiedName(), variableName, variableScope.getFilePath());
		isPrimitive = false;
	}

	public ParsedVariable(String blockNamePrefix, String blockName, String filePath) {
		super(blockNamePrefix, blockName, filePath);
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

	@Override
	public AccessSpecifier getAccessSpecifier() {
		return null;
	}
}
