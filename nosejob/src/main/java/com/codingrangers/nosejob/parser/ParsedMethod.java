package com.codingrangers.nosejob.parser;

import java.util.ArrayList;
import java.util.List;
import com.codingrangers.nosejob.models.IMethodData;
import com.codingrangers.nosejob.models.IVariableData;
import com.codingrangers.nosejob.helpers.DataStructureHelpers;
import com.codingrangers.nosejob.models.IClassData;

/**
 * ParsedMethod TODO: Need to unit test this
 */
public class ParsedMethod extends ParsedCodeUnit implements IMethodData {

	private IVariableData returnType;
	private String className;
	private List<IVariableData> parameters;
	private List<IVariableData> localVariables;

	public ParsedMethod(IClassData methodClass, String methodName) {
		super(methodClass.getFullyQualifiedName(), methodName, methodClass.getFilePath());
		className = methodClass.getName();
		parameters = new ArrayList<IVariableData>();
		localVariables = new ArrayList<IVariableData>();
	}

	public void addReturnType(IVariableData methodReturnType) {
		returnType = methodReturnType;
	}

	public void addParameter(IVariableData newParameter) {
		parameters.add(newParameter);
	}

	public void addVariable(IVariableData newVariable) {
		localVariables.add(newVariable);
	}

	@Override
	public String getClassName() {
		return className;
	}

	@Override
	public IVariableData getReturnType() {
		return returnType;
	}

	@Override
	public boolean hasPrimitiveReturnType() {
		return returnType.isPrimitive();
	}

	@Override
	public List<IVariableData> getParameters() {
		return new ArrayList<IVariableData>(parameters);
	}

	@Override
	public List<IVariableData> getLocalVariables() {
		return new ArrayList<IVariableData>(localVariables);
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
