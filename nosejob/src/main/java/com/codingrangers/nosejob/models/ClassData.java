package com.codingrangers.nosejob.models;

import java.util.List;

public interface ClassData extends CodeData {
	List<String> getMethodSignatures();

	MethodData getMethod(String signature);

	List<String> getFieldsNames();

	VariableData getField(String name);
}
