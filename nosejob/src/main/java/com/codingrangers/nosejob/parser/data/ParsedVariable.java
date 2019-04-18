package com.codingrangers.nosejob.parser.data;

import com.codingrangers.nosejob.models.AccessSpecifier;
import com.codingrangers.nosejob.models.VariableData;

public class ParsedVariable extends ParsedCodeUnit implements VariableData, Cloneable {

	private String type;
	private boolean isPrimitive;
	private AccessSpecifier accessSpecifier;

	ParsedVariable(String namePrefix, String variableName, String filePath) {
		super(namePrefix, variableName, filePath);
		isPrimitive = false;
		accessSpecifier = AccessSpecifier.PRIVATE;
	}

	void setIsPrimitive() {
		isPrimitive = true;
	}

	void setVariableType(String variableType) {
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

	public void setAccessSpecifier(AccessSpecifier accessSpecifier) {
		this.accessSpecifier = accessSpecifier;
	}
	
	@Override
	public AccessSpecifier getAccessSpecifier() {
		return accessSpecifier;
	}

	@Override
	protected ParsedVariable clone() {
		try {
			return (ParsedVariable) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException();
		}
	}
}
