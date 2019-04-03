package com.codingrangers.nosejob.parser;

import java.util.*;

import com.codingrangers.nosejob.models.IVariableData;
import com.codingrangers.nosejob.models.IClassData;
import com.codingrangers.nosejob.models.IMethodData;

/**
 * ParsedClass TODO: Need to unit test this
 */
public class ParsedClass extends ParsedCodeUnit implements IClassData {

	private Map<String, IMethodData> classMethods;
	private Map<String, IVariableData> classVariables;

	public ParsedClass(String classNamePrefix, String className, String filePath) {
		super(classNamePrefix, className, filePath);
		classMethods = new HashMap<>();
		classVariables = new HashMap<>();
	}

	public void addMethod(IMethodData newMethod) {
		classMethods.put(newMethod.getName(), newMethod);
	}

	public void addField(IVariableData newVariable) {
		classVariables.put(newVariable.getName(), newVariable);
	}

	// @Override
	// public List<String> getMethodNames() {
	// return new ArrayList<String>(classMethods.keySet());
	// }

	@Override
	public IMethodData getMethod(String name) {
		return classMethods.get(name);
	}

	@Override
	public List<String> getFieldsNames() {
		return new ArrayList<String>(classVariables.keySet());
	}

	@Override
	public IVariableData getField(String name) {
		return classVariables.get(name);
	}

	@Override
	public List<String> getMethodSignatures() {
		return null;
	}
}
