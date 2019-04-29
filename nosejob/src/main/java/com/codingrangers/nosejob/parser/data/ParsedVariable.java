/* NOSEJOB by The Coding Rangers
 * William Akinsanya
 * Alessandro Baccin
 * Peter Major
 * Adrian Wennberg
 * For the UCD module:
 * 	 Software Engineering Project 3
 */
 package com.codingrangers.nosejob.parser.data;

import com.codingrangers.nosejob.models.AccessSpecifier;
import com.codingrangers.nosejob.models.VariableData;

public class ParsedVariable extends ParsedCodeUnit implements VariableData, Cloneable {

	private String type;
	private boolean isPrimitive;
	private boolean isStatic;
	private AccessSpecifier accessSpecifier;

	ParsedVariable(String namePrefix, String variableName, String filePath) {
		super(namePrefix, variableName, filePath);
		isPrimitive = false;
		isStatic = false;
		accessSpecifier = AccessSpecifier.PRIVATE;
	}

	public void setIsPrimitive() {
		isPrimitive = true;
	}

	public void setIsStatic() {
		isStatic = true;
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
	public boolean isStatic() {
		return isStatic;
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
