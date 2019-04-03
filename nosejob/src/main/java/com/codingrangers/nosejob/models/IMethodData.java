package com.codingrangers.nosejob.models;

import java.util.List;

public interface IMethodData extends ICodeData {
	String getClassName();

	IVariableData getReturnType();

	boolean hasPrimitiveReturnType();

	List<IVariableData> getParameters();

	List<IVariableData> getLocalVariables();

	int getPrimitiveParameterCount();

	int getPrimitiveLocalCount();
}
