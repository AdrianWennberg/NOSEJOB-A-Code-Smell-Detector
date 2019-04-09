package com.codingrangers.nosejob.parser.data;

import com.codingrangers.nosejob.models.CodeData;
import com.codingrangers.nosejob.models.MethodData;
import com.codingrangers.nosejob.models.VariableData;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * ParsedClassTest
 */

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
	public void canAddMultipleFields() {
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
}
