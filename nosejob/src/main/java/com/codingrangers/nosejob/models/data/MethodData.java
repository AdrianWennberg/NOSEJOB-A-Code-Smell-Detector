package com.codingrangers.nosejob.models.data;

import java.util.List;

public interface MethodData extends CodeData {
	String getClassName();

	VariableData getReturnType();

	boolean hasPrimitiveReturnType();

	List<VariableData> getParameters();

	List<VariableData> getLocalVariables();

	int getPrimitiveParameterCount();

	int getPrimitiveLocalCount();
}
