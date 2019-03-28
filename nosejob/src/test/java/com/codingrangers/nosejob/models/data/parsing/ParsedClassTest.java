package com.codingrangers.nosejob.models.data.parsing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import com.codingrangers.nosejob.models.data.CodeData;
import com.codingrangers.nosejob.models.data.MethodData;
import com.codingrangers.nosejob.models.data.VariableData;

import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;

import java.util.List;

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
	public void endLineLessThanstartLine() {
		ParsedClass parsedClass = new ParsedClass("", "", "");
		parsedClass.setStartLine(10);
		parsedClass.setEndLine(9);
	}

	@Test(expected = IllegalStateException.class)
	public void startLineAllreadySet() {
		ParsedClass parsedClass = new ParsedClass("", "", "");
		parsedClass.setStartLine(10);
		parsedClass.setStartLine(10);
	}

	@Test(expected = IllegalStateException.class)
	public void endLineAllreadySet() {
		ParsedClass parsedClass = new ParsedClass("", "", "");
		parsedClass.setEndLine(12);
		parsedClass.setEndLine(12);
	}

	@Test
	public void canAddMethod() {
		String methodSignature = "testMethod";
		MethodData mockedMethod = mock(MethodData.class);
		ParsedClass parsedClass = new ParsedClass("", "", "");

		when(mockedMethod.getName()).thenReturn(methodSignature);

		parsedClass.addMethod(mockedMethod);

		List<String> methods = parsedClass.getMethodNames();

		assertEquals(1, methods.size());
		assertEquals(methodSignature, methods.get(0));
		assertEquals(mockedMethod, parsedClass.getMethod(methodSignature));
	}

	@Test
	public void canAddMultipleMethods() {
		String firstMethodSignature = "testMethodOne";
		MethodData firstMockedMethod = mock(MethodData.class);
		when(firstMockedMethod.getName()).thenReturn(firstMethodSignature);

		String secondMethodSignature = "testMethodTwo";
		MethodData secondMockedMethod = mock(MethodData.class);
		when(secondMockedMethod.getName()).thenReturn(secondMethodSignature);

		ParsedClass parsedClass = new ParsedClass("", "", "");

		parsedClass.addMethod(firstMockedMethod);
		parsedClass.addMethod(secondMockedMethod);

		List<String> methods = parsedClass.getMethodNames();

		assertEquals(2, methods.size());
		assertThat(methods, containsInAnyOrder(firstMethodSignature, secondMethodSignature));
		assertEquals(firstMockedMethod, parsedClass.getMethod(firstMethodSignature));
		assertEquals(secondMockedMethod, parsedClass.getMethod(secondMethodSignature));
	}

	@Test
	public void canAddField() {
		String fieldName = "testField";
		VariableData mockedField = mock(VariableData.class);
		ParsedClass parsedClass = new ParsedClass("", "", "");

		when(mockedField.getName()).thenReturn(fieldName);

		parsedClass.addField(mockedField);

		List<String> fields = parsedClass.getFieldsNames();

		assertEquals(1, fields.size());
		assertEquals(fieldName, fields.get(0));
		assertEquals(mockedField, parsedClass.getField(fieldName));
	}

	@Test
	public void canAddMultipleFields() {
		String firstFieldName = "testFieldOne";
		VariableData firstMockedField = mock(VariableData.class);
		when(firstMockedField.getName()).thenReturn(firstFieldName);

		String secondFieldName = "testFieldTwo";
		VariableData secondMockedField = mock(VariableData.class);
		when(secondMockedField.getName()).thenReturn(secondFieldName);

		ParsedClass parsedClass = new ParsedClass("", "", "");

		parsedClass.addField(firstMockedField);
		parsedClass.addField(secondMockedField);

		List<String> fields = parsedClass.getFieldsNames();

		assertEquals(2, fields.size());
		assertThat(fields, containsInAnyOrder(firstFieldName, secondFieldName));
		assertEquals(firstMockedField, parsedClass.getField(firstFieldName));
		assertEquals(secondMockedField, parsedClass.getField(secondFieldName));
	}
}