package com.codingrangers.nosejob.models;

import java.util.List;

public interface IClassData extends ICodeData {
	List<String> getMethodSignatures();

	IMethodData getMethod(String signature);

	List<String> getFieldsNames();

	IVariableData getField(String name);
}
