package com.codingrangers.nosejob.parser;

import java.util.*;

import com.codingrangers.nosejob.models.VariableData;
import com.codingrangers.nosejob.models.AccessSpecifier;
import com.codingrangers.nosejob.models.ClassData;
import com.codingrangers.nosejob.models.FieldReferance;
import com.codingrangers.nosejob.models.MethodData;
import com.codingrangers.nosejob.models.MethodReference;

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

	@Override
	public int countMethods() {
		return classMethods.size();
	}

	@Override
	public List<String> getMethodSignatures() {
		return new ArrayList<String>(classMethods.keySet());
	}

	@Override
	public MethodData getMethod(String name) {
		return classMethods.get(name);
	}

	public void addField(VariableData newVariable) {
		classVariables.put(newVariable.getName(), newVariable);
	}

	@Override
	public int countFields() {
		return getFieldsNames().size();
	}

	@Override
	public int countPublicFields() {
		int count = 0;
		for (String fieldName : getFieldsNames()) {
			if(getField(fieldName).getAccessSpecifier() == AccessSpecifier.PUBLIC) {
				count++;
			}
		}
		return count;
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
	public int countInternalMethodCalls() {
		return countMethodCallsTo(getFullyQualifiedName());
	}

	@Override
	public List<MethodReference> getInternalMethodCalls() {
		return getMethodCallsTo(getFullyQualifiedName());
	}

	@Override
	public int countMethodCallsTo(String fullyQualifedClassName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MethodReference> getMethodCallsTo(String fullyQualifedClassName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countFieldReferencesTo(String fullyQualifedClassName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<FieldReferance> getFieldReferancesTo(String fullyQualifedClassName) {
		// TODO Auto-generated method stub
		return null;
	}
}
