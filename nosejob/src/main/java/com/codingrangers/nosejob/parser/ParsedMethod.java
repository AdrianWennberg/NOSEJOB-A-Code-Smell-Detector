package com.codingrangers.nosejob.parser;

import java.util.ArrayList;
import java.util.List;
import com.codingrangers.nosejob.models.MethodData;
import com.codingrangers.nosejob.models.VariableData;
import com.codingrangers.nosejob.helpers.DataStructureHelpers;
import com.codingrangers.nosejob.models.ClassData;

/**
 * ParsedMethod TODO: Need to unit test this
 */
public class ParsedMethod extends ParsedCodeUnit implements MethodData {

	private VariableData returnType;
	private String className;
	private List<VariableData> parameters;
	private List<VariableData> localVariables;

	ParsedMethod(ClassData methodClass, String methodName) {
		super(methodClass.getFullyQualifiedName(), methodName, methodClass.getFilePath());
		className = methodClass.getName();
		parameters = new ArrayList<VariableData>();
		localVariables = new ArrayList<VariableData>();
	}

	public void addReturnType(VariableData methodReturnType) {
		returnType = methodReturnType;
	}

	public void addParameter(VariableData newParameter) {
		parameters.add(newParameter);
	}

	public void addVariable(VariableData newVariable) {
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
		return new ArrayList<VariableData>(parameters);
	}

	@Override
	public List<VariableData> getLocalVariables() {
		return new ArrayList<VariableData>(localVariables);
	}

	@Override
	public int getPrimitiveParameterCount() {
		return DataStructureHelpers.countPrimitives(parameters);
	}

	@Override
	public int getPrimitiveLocalCount() {
		return DataStructureHelpers.countPrimitives(localVariables);
	}

}
