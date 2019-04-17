package com.codingrangers.nosejob.models;

import java.util.List;

public interface ClassData extends CodeData {
	int countPublicFields();

	int countTotalMehodCalls();

	int countInternalMethodCalls();
<<<<<<< HEAD

	Iterable<MethodReference> getInternalMethodCalls();

	int countMethodCallsTo(String fullyQualifiedClassName);

	Iterable<MethodReference> getMethodCallsTo(String fullyQualifiedClassName);

	int countFieldReferencesTo(String fullyQualifiedClassName);

	Iterable<FieldReference> getFieldReferencesTo(String fullyQualifiedClassName);
=======
	List<MethodReference> getInternalMethodCalls();

	int countMethodCallsTo(String fullyQualifedClassName);
	List<MethodReference> getMethodCallsTo(String fullyQualifedClassName);

	int countFieldReferencesTo(String fullyQualifedClassName);
	List<FieldReference> getFieldReferancesTo(String fullyQualifedClassName);
>>>>>>> Violation of Data privacy Tested

	int countMethods();
	List<String> getMethodSignatures();
	MethodData getMethod(String signature);

	int countFields();
	List<String> getFieldsNames();
	VariableData getField(String name);

    int countPrimitiveFields();
}
