package com.codingrangers.nosejob.parser;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

import com.codingrangers.nosejob.models.VariableData;
import com.codingrangers.nosejob.models.ClassData;
import com.codingrangers.nosejob.models.MethodData;

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

	public void addField(VariableData newVariable) {
		classVariables.put(newVariable.getName(), newVariable);
	}

	public ParsedMethod createMethod(String name) {
		ParsedMethod method = new ParsedMethod(this, name);
		classMethods.put(method.getName(), method);
		return method;
	}

	public ParsedVariable createField(String name) {
		ParsedVariable variable = new ParsedVariable(this, name);
		classVariables.put(variable.getName(), variable);
		return variable;
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

	@Override
	public List<String> getMethodSignatures() {
		return new ArrayList<String>(classMethods.keySet());
	}
}
