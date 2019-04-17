package com.codingrangers.nosejob.models;

import java.util.List;

public interface ClassData extends CodeData {
	int countPublicFields();

	int countTotalMehodCalls();

	int countInternalMethodCalls();
	List<MethodReference> getInternalMethodCalls();

	int countMethodCallsTo(String fullyQualifedClassName);
	List<MethodReference> getMethodCallsTo(String fullyQualifedClassName);

	int countFieldReferencesTo(String fullyQualifedClassName);
	List<FieldReference> getFieldReferancesTo(String fullyQualifedClassName);

	int countMethods();
	List<String> getMethodSignatures();
	MethodData getMethod(String signature);

	int countFields();
	List<String> getFieldsNames();
	VariableData getField(String name);
}
