package com.codingrangers.nosejob.models;

import java.util.List;

public interface ClassData extends CodeData {
	int countPublicFields();

	int countInternalMethodCalls();

	Iterable<MethodReference> getInternalMethodCalls();

	int countMethodCallsTo(String fullyQualifiedClassName);

	Iterable<MethodReference> getMethodCallsTo(String fullyQualifiedClassName);

	int countFieldReferencesTo(String fullyQualifiedClassName);

	Iterable<FieldReference> getFieldReferencesTo(String fullyQualifiedClassName);

	int countMethods();
	List<String> getMethodSignatures();
	MethodData getMethod(String signature);

	int countFields();
	List<String> getFieldsNames();
	VariableData getField(String name);

    int countPrimitiveFields();
}
