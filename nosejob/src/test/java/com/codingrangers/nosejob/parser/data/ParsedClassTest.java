/* NOSEJOB by The Coding Rangers
 * William Akinsanya
 * Alessandro Baccin
 * Peter Major
 * Adrian Wennberg
 * For the UCD module:
 * 	 Software Engineering Project 3
 */
 package com.codingrangers.nosejob.parser.data;

import com.codingrangers.nosejob.models.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;


public class ParsedClassTest {
	@Test
	public void nameIsSetCorrectly() {
		String className = "ClassName";
		String namePrefix = "name.prefix";
		String fullyQualifiedName = "name.prefix.ClassName";
		CodeData parsedClass = new ParsedClass(namePrefix, className, "");

		assertEquals(className, parsedClass.getName());
		assertEquals(fullyQualifiedName, parsedClass.getFullyQualifiedName());

		className = "OtherClassName";
		namePrefix = "other.name.prefix";
		fullyQualifiedName = "other.name.prefix.OtherClassName";
		parsedClass = new ParsedClass(namePrefix, className, "");

		assertEquals(className, parsedClass.getName());
		assertEquals(fullyQualifiedName, parsedClass.getFullyQualifiedName());
	}

	@Test
	public void pathSetCorrectly() {
		String path = "C:/File/path.java";
		CodeData parsedClass = new ParsedClass("", "", path);

		assertEquals(path, parsedClass.getFilePath());

		path = "C:/File/other/path.java";
		parsedClass = new ParsedClass("", "", path);

		assertEquals(path, parsedClass.getFilePath());
	}

	@Test
	public void canSetLineNumbers() {
		int startLine = 5;
		int endLine = 100;
		int lineCount = 96;

		ParsedClass parsedClass = new ParsedClass("", "", "");
		parsedClass.setStartLine(startLine);
		parsedClass.setEndLine(endLine);

		assertEquals(startLine, parsedClass.getStartLineNumber());
		assertEquals(endLine, parsedClass.getEndLineNumber());
		assertEquals(lineCount, parsedClass.getLineCount());

		startLine = 10;
		endLine = 10;
		lineCount = 1;

		parsedClass = new ParsedClass("", "", "");
		parsedClass.setStartLine(startLine);
		parsedClass.setEndLine(endLine);

		assertEquals(startLine, parsedClass.getStartLineNumber());
		assertEquals(endLine, parsedClass.getEndLineNumber());
		assertEquals(lineCount, parsedClass.getLineCount());
	}

	@Test(expected = IllegalArgumentException.class)
	public void negativeStartLine() {
		ParsedClass parsedClass = new ParsedClass("", "", "");
		parsedClass.setStartLine(-5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void negativeEndLine() {
		ParsedClass parsedClass = new ParsedClass("", "", "");
		parsedClass.setEndLine(-4);
	}

	@Test(expected = IllegalArgumentException.class)
	public void startLineGreaterThanEndLine() {
		ParsedClass parsedClass = new ParsedClass("", "", "");
		parsedClass.setEndLine(5);
		parsedClass.setStartLine(6);
	}

	@Test(expected = IllegalArgumentException.class)
	public void endLineLessThanStartLine() {
		ParsedClass parsedClass = new ParsedClass("", "", "");
		parsedClass.setStartLine(10);
		parsedClass.setEndLine(9);
	}

	@Test(expected = IllegalStateException.class)
	public void startLineAlreadySet() {
		ParsedClass parsedClass = new ParsedClass("", "", "");
		parsedClass.setStartLine(10);
		parsedClass.setStartLine(10);
	}

	@Test(expected = IllegalStateException.class)
	public void endLineAlreadySet() {
		ParsedClass parsedClass = new ParsedClass("", "", "");
		parsedClass.setEndLine(12);
		parsedClass.setEndLine(12);
	}

	@Test
	public void canCreateMethod() {
		ParsedMethod mockedPrototype = mock(ParsedMethod.class);
		ParsedMethod mockedClone = mock(ParsedMethod.class);
		String methodSignature = "testMethod";

		when(mockedPrototype.clone()).thenReturn(mockedClone);
		when(mockedClone.getName()).thenReturn(methodSignature);

		ParsedClass parsedClass = new ParsedClass("", "", "");

		parsedClass.setMethodPrototype(mockedPrototype);
		MethodData methodData = parsedClass.createMethod(methodSignature);

		List<String> methods = parsedClass.getMethodSignatures();

		assertEquals(1, methods.size());
		assertEquals(methodSignature, methods.get(0));
		assertEquals(methodData, parsedClass.getMethod(methodSignature));
		assertEquals(mockedClone, methodData);
	}
	@Test
	public void canCreateMultipleMethods() {
		ParsedMethod mockedPrototype = mock(ParsedMethod.class);

		String firstMethodSignature = "testMethodOne";
		ParsedMethod firstMockedMethod = mock(ParsedMethod.class);
		when(firstMockedMethod.getName()).thenReturn(firstMethodSignature);

		String secondMethodSignature = "testMethodTwo";
		ParsedMethod secondMockedMethod = mock(ParsedMethod.class);
		when(secondMockedMethod.getName()).thenReturn(secondMethodSignature);

		ParsedClass parsedClass = new ParsedClass("", "", "");
		parsedClass.setMethodPrototype(mockedPrototype);

		when(mockedPrototype.clone()).thenReturn(firstMockedMethod);
		MethodData firstMethodData = parsedClass.createMethod(firstMethodSignature);
		when(mockedPrototype.clone()).thenReturn(secondMockedMethod);
		MethodData secondMethodData = parsedClass.createMethod(secondMethodSignature);

		List<String> methods = parsedClass.getMethodSignatures();

		assertEquals(2, methods.size());
		assertThat(methods, containsInAnyOrder(firstMethodSignature, secondMethodSignature));
		assertEquals(firstMockedMethod, parsedClass.getMethod(firstMethodSignature));
		assertEquals(secondMockedMethod, parsedClass.getMethod(secondMethodSignature));
		assertEquals(firstMockedMethod, firstMethodData);
		assertEquals(secondMockedMethod, secondMethodData);
	}

	@Test
	public void canCreateDefaultMethod() {
		String methodSignature = "testMethod";

		ParsedClass parsedClass = new ParsedClass("", "", "");
		MethodData methodData = parsedClass.createMethod(methodSignature);

		List<String> methods = parsedClass.getMethodSignatures();

		assertEquals(1, methods.size());
		assertEquals(methodSignature, methods.get(0));
		assertEquals(methodData, parsedClass.getMethod(methodSignature));
	}

	@Test
	public void canCreateMultipleDefaultMethods() {

		String firstMethodSignature = "testMethodOne";

		String secondMethodSignature = "testMethodTwo";

		ParsedClass parsedClass = new ParsedClass("", "", "");

		MethodData firstMethodData = parsedClass.createMethod(firstMethodSignature);
		MethodData secondMethodData = parsedClass.createMethod(secondMethodSignature);

		List<String> methods = parsedClass.getMethodSignatures();

		assertEquals(2, methods.size());
		assertThat(methods, containsInAnyOrder(firstMethodSignature, secondMethodSignature));
		assertEquals(firstMethodData, parsedClass.getMethod(firstMethodSignature));
		assertEquals(secondMethodData, parsedClass.getMethod(secondMethodSignature));
	}
	@Test
	public void canCreateField() {

		ParsedVariable mockedPrototype = mock(ParsedVariable.class);
		ParsedVariable mockedClone = mock(ParsedVariable.class);
		String fieldName = "testField";

		when(mockedPrototype.clone()).thenReturn(mockedClone);
		when(mockedClone.getName()).thenReturn(fieldName);

		ParsedClass parsedClass = new ParsedClass("", "", "");
		parsedClass.setFieldPrototype(mockedPrototype);
		VariableData fieldData = parsedClass.createField(fieldName);

		List<String> fields = parsedClass.getFieldsNames();

		assertEquals(1, fields.size());
		assertEquals(fieldName, fields.get(0));
		assertEquals(mockedClone, parsedClass.getField(fieldName));
		assertEquals(mockedClone, fieldData);
	}
	@Test
	public void canCreateMultipleFields() {
		ParsedVariable mockedPrototype = mock(ParsedVariable.class);

		String firstFieldName = "testFieldOne";
		ParsedVariable firstMockedField = mock(ParsedVariable.class);
		when(firstMockedField.getName()).thenReturn(firstFieldName);

		String secondFieldName = "testFieldTwo";
		ParsedVariable secondMockedField = mock(ParsedVariable.class);
		when(secondMockedField.getName()).thenReturn(secondFieldName);

		ParsedClass parsedClass = new ParsedClass("", "", "");
		parsedClass.setFieldPrototype(mockedPrototype);


		when(mockedPrototype.clone()).thenReturn(firstMockedField);
		ParsedVariable firstFieldData = parsedClass.createField(firstFieldName);
		when(mockedPrototype.clone()).thenReturn(secondMockedField);
		ParsedVariable secondFieldData = parsedClass.createField(secondFieldName);

		List<String> fieldsNames = parsedClass.getFieldsNames();

		assertEquals(2, fieldsNames.size());
		assertThat(fieldsNames, containsInAnyOrder(firstFieldName, secondFieldName));
		assertEquals(firstMockedField, parsedClass.getField(firstFieldName));
		assertEquals(secondMockedField, parsedClass.getField(secondFieldName));
		assertEquals(firstMockedField, firstFieldData);
		assertEquals(secondMockedField, secondFieldData);
	}

	@Test
	public void canCreateDefaultField() {

		String fieldName = "testField";

		ParsedClass parsedClass = new ParsedClass("", "", "");
		VariableData fieldData = parsedClass.createField(fieldName);

		List<String> fields = parsedClass.getFieldsNames();

		assertEquals(1, fields.size());
		assertEquals(fieldName, fields.get(0));
		assertEquals(fieldData, parsedClass.getField(fieldName));
	}

	@Test
	public void canCreateMultipleDefaultFields() {

		String firstFieldName = "testFieldOne";

		String secondFieldName = "testFieldTwo";

		ParsedClass parsedClass = new ParsedClass("", "", "");

		ParsedVariable firstFieldData = parsedClass.createField(firstFieldName);
		ParsedVariable secondFieldData = parsedClass.createField(secondFieldName);

		List<String> fieldsNames = parsedClass.getFieldsNames();

		assertEquals(2, fieldsNames.size());
		assertThat(fieldsNames, containsInAnyOrder(firstFieldName, secondFieldName));
		assertEquals(firstFieldData, parsedClass.getField(firstFieldName));
		assertEquals(secondFieldData, parsedClass.getField(secondFieldName));
	}


	@Test
	public void testCountMethods() {
		ParsedClass parsedClass = new ParsedClass("", "", "");

		assertEquals(0, parsedClass.countMethods());
		parsedClass.createMethod("a");
		assertEquals(1, parsedClass.countMethods());
		parsedClass.createMethod("b");
		assertEquals(2, parsedClass.countMethods());
	}

	@Test
	public void testCountFields() {
		ParsedClass parsedClass = new ParsedClass("", "", "");

		assertEquals(0, parsedClass.countFields());
		parsedClass.createField("a");
		assertEquals(1, parsedClass.countFields());
		parsedClass.createField("b");
		assertEquals(2, parsedClass.countFields());
	}

	@Test
	public void testCountPublicFields() {
		ParsedClass parsedClass = new ParsedClass("", "", "");

		assertEquals(0, parsedClass.countPublicFields());

		ParsedVariable createdField = parsedClass.createField("a");
		createdField.setAccessSpecifier(AccessSpecifier.PUBLIC);
		assertEquals(1, parsedClass.countPublicFields());

		createdField = parsedClass.createField("b");
		assertEquals(1, parsedClass.countPublicFields());

		createdField.setAccessSpecifier(AccessSpecifier.PUBLIC);
		assertEquals(2, parsedClass.countPublicFields());
	}

	@Test
	public void canAddMethodReferences() {
		String className = "ClassName";
		String namePrefix = "name.prefix";
		String fullyQualifiedName = "name.prefix.ClassName";
		ParsedClass parsedClass = new ParsedClass(namePrefix, className, "");

		ReferenceStorage mockedStorage = mock(ReferenceStorage.class);
		parsedClass.setReferenceStorage(mockedStorage);


		String otherClassName = "name.other.OtherClassName";
		String methodSignature = "foo()";

		MethodReference reference = parsedClass.addReferenceToMethod(otherClassName, methodSignature);

        assertEquals(fullyQualifiedName, reference.getReferencingClassName());
        assertEquals(otherClassName, reference.getReferredClassName());
		assertEquals(methodSignature, reference.getReferredMethodSignature());

		verify(mockedStorage, times(1)).add(reference);
	}

	@Test
	public void canGetMethodReferences() {
		String className = "ClassName";
		String namePrefix = "name.prefix";
		String fullyQualifiedName = "name.prefix.ClassName";
		ParsedClass parsedClass = new ParsedClass(namePrefix, className, "");

		ReferenceStorage mockedStorage = mock(ReferenceStorage.class);
		parsedClass.setReferenceStorage(mockedStorage);

		MethodReference mockedReference = mock(MethodReference.class);

		List<MethodReference> references = new ArrayList<>();
		references.add(mockedReference);
		references.add(mockedReference);
		references.add(mockedReference);

		String otherClassName = "name.other.OtherClassName";

		when(mockedStorage.getMethodReference(fullyQualifiedName, otherClassName)).thenReturn(references);


		Iterable<MethodReference> methodReferences = parsedClass.getMethodCallsTo(otherClassName);

		assertEquals(references, methodReferences);
		assertEquals(3, parsedClass.countMethodCallsTo(otherClassName));
	}

	@Test
	public void canGetInternalMethodReferences() {
		String className = "ClassName";
		String namePrefix = "name.prefix";
		String fullyQualifiedName = "name.prefix.ClassName";
		ParsedClass parsedClass = new ParsedClass(namePrefix, className, "");

		ReferenceStorage mockedStorage = mock(ReferenceStorage.class);
		parsedClass.setReferenceStorage(mockedStorage);

		MethodReference mockedReference = mock(MethodReference.class);

		List<MethodReference> references = new ArrayList<>();
		references.add(mockedReference);
		references.add(mockedReference);
		references.add(mockedReference);


		when(mockedStorage.getMethodReference(fullyQualifiedName, fullyQualifiedName)).thenReturn(references);


		Iterable<MethodReference> methodReferences = parsedClass.getInternalMethodCalls();

		assertEquals(references, methodReferences);
		assertEquals(3, parsedClass.countInternalMethodCalls());
	}

	@Test
	public void canAddFieldReferences() {
		String className = "ClassName";
		String namePrefix = "name.prefix";
		String fullyQualifiedName = "name.prefix.ClassName";
		ParsedClass parsedClass = new ParsedClass(namePrefix, className, "");

		ReferenceStorage mockedStorage = mock(ReferenceStorage.class);
		parsedClass.setReferenceStorage(mockedStorage);
		String otherClassName = "name.other.OtherClassName";
		String fieldName = "foo";

		FieldReference reference = parsedClass.addReferenceToField(otherClassName, fieldName);

        assertEquals(fullyQualifiedName, reference.getReferencingClassName());
        assertEquals(otherClassName, reference.getReferredClassName());
		assertEquals(fieldName, reference.getReferredFieldName());

		verify(mockedStorage, times(1)).add(reference);
	}

	@Test
	public void canGetFieldReferences() {
		String className = "ClassName";
		String namePrefix = "name.prefix";
		String fullyQualifiedName = "name.prefix.ClassName";
		ParsedClass parsedClass = new ParsedClass(namePrefix, className, "");

		ReferenceStorage mockedStorage = mock(ReferenceStorage.class);
		parsedClass.setReferenceStorage(mockedStorage);

		FieldReference mockedReference = mock(FieldReference.class);

		List<FieldReference> references = new ArrayList<>();
		references.add(mockedReference);
		references.add(mockedReference);
		references.add(mockedReference);

		String otherClassName = "name.other.OtherClassName";

		when(mockedStorage.getFieldReference(fullyQualifiedName, otherClassName)).thenReturn(references);


		Iterable<FieldReference> methodReferences = parsedClass.getFieldReferencesTo(otherClassName);

		assertEquals(references, methodReferences);
		assertEquals(3, parsedClass.countFieldReferencesTo(otherClassName));
	}

	@Test
	public void canClone() {
		String className = "ClassName";
		String namePrefix = "name.prefix";
		String fullyQualifiedName = "name.prefix.ClassName";
		String path = "C:/File/path.java";
		ParsedClass parsedClass = new ParsedClass(namePrefix, className, path);

		assertEquals(className, parsedClass.getName());
		assertEquals(fullyQualifiedName, parsedClass.getFullyQualifiedName());
		assertEquals(path, parsedClass.getFilePath());

		CodeData clonedClass = parsedClass.clone();

		assertEquals(className, clonedClass.getName());
		assertEquals(fullyQualifiedName, clonedClass.getFullyQualifiedName());
		assertEquals(path, clonedClass.getFilePath());
	}
}
