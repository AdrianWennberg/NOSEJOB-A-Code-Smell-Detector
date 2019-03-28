package com.codingrangers.nosejob.models.data.parsing;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;

import com.codingrangers.nosejob.models.data.ClassData;
import com.codingrangers.nosejob.models.data.CodeData;
import com.codingrangers.nosejob.models.data.VariableData;

public class ParsedMethodTest {
	@Test
	public void nameIsSetCorrectly() {
		String fullyQualifiedClassName = "name.prefix.ClassName";
		ClassData mockedClass = mock(ClassData.class);
		when(mockedClass.getFullyQualifiedName()).thenReturn(fullyQualifiedClassName);

		String methodSignature = "methodName(int)";
		String fullyQuallifiedMethodName = "name.prefix.ClassName.methodName(int)";

		CodeData parsedMethod = new ParsedMethod(mockedClass, methodSignature);

		assertEquals(methodSignature, parsedMethod.getName());
		assertEquals(fullyQuallifiedMethodName, parsedMethod.getFullyQualifiedName());

		fullyQualifiedClassName = "other.name.prefix.OtherClassName";
		mockedClass = mock(ClassData.class);
		when(mockedClass.getFullyQualifiedName()).thenReturn(fullyQualifiedClassName);

		methodSignature = "otherMethodName(int, float)";
		fullyQuallifiedMethodName = "other.name.prefix.OtherClassName.otherMethodName(int, float)";

		parsedMethod = new ParsedMethod(mockedClass, methodSignature);

		assertEquals(methodSignature, parsedMethod.getName());
		assertEquals(fullyQuallifiedMethodName, parsedMethod.getFullyQualifiedName());
	}

	@Test
	public void pathSetCorrectly() {
		String path = "C:/File/path.java";
		ClassData mockedClass = mock(ClassData.class);
		when(mockedClass.getFilePath()).thenReturn(path);

		CodeData parsedMethod = new ParsedMethod(mockedClass, "");

		assertEquals(path, parsedMethod.getFilePath());

		path = "C:/File/other/path.java";
		mockedClass = mock(ClassData.class);
		when(mockedClass.getFilePath()).thenReturn(path);

		parsedMethod = new ParsedMethod(mockedClass, "");

		assertEquals(path, parsedMethod.getFilePath());
	}

	@Test
	public void canSetLineNumbers() {
		int startLine = 5;
		int endLine = 100;
		int lineCount = 96;

		ClassData mockedClass = mock(ClassData.class);
		ParsedMethod parsedMethod = new ParsedMethod(mockedClass, "");

		parsedMethod.setStartLine(startLine);
		parsedMethod.setEndLine(endLine);

		assertEquals(startLine, parsedMethod.getStartLineNumber());
		assertEquals(endLine, parsedMethod.getEndLineNumber());
		assertEquals(lineCount, parsedMethod.getLineCount());

		startLine = 10;
		endLine = 10;
		lineCount = 1;

		parsedMethod = new ParsedMethod(mockedClass, "");
		parsedMethod.setStartLine(startLine);
		parsedMethod.setEndLine(endLine);

		assertEquals(startLine, parsedMethod.getStartLineNumber());
		assertEquals(endLine, parsedMethod.getEndLineNumber());
		assertEquals(lineCount, parsedMethod.getLineCount());
	}

	@Test(expected = IllegalArgumentException.class)
	public void negativeStartLine() {
		ClassData mockedClass = mock(ClassData.class);
		ParsedMethod parsedMethod = new ParsedMethod(mockedClass, "");
		parsedMethod.setStartLine(-5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void negativeEndLine() {
		ClassData mockedClass = mock(ClassData.class);
		ParsedMethod parsedMethod = new ParsedMethod(mockedClass, "");
		parsedMethod.setEndLine(-4);
	}

	@Test(expected = IllegalArgumentException.class)
	public void startLineGreaterThanEndLine() {
		ClassData mockedClass = mock(ClassData.class);
		ParsedMethod parsedMethod = new ParsedMethod(mockedClass, "");
		parsedMethod.setEndLine(5);
		parsedMethod.setStartLine(6);
	}

	@Test(expected = IllegalArgumentException.class)
	public void endLineLessThanstartLine() {
		ClassData mockedClass = mock(ClassData.class);
		ParsedMethod parsedMethod = new ParsedMethod(mockedClass, "");
		parsedMethod.setStartLine(10);
		parsedMethod.setEndLine(9);
	}

	@Test(expected = IllegalStateException.class)
	public void startLineAllreadySet() {
		ClassData mockedClass = mock(ClassData.class);
		ParsedMethod parsedMethod = new ParsedMethod(mockedClass, "");
		parsedMethod.setStartLine(10);
		parsedMethod.setStartLine(10);
	}

	@Test(expected = IllegalStateException.class)
	public void endLineAllreadySet() {
		ClassData mockedClass = mock(ClassData.class);
		ParsedMethod parsedMethod = new ParsedMethod(mockedClass, "");
		parsedMethod.setEndLine(12);
		parsedMethod.setEndLine(12);
	}

	@Test
	public void canGetClassName() {
		String className = "testClass";
		ClassData mockedClass = mock(ClassData.class);
		when(mockedClass.getName()).thenReturn(className);

		ParsedMethod parsedMethod = new ParsedMethod(mockedClass, "");

		assertEquals(className, parsedMethod.getClassName());
	}

	@Test
	public void canAddReturnType() {
		VariableData mockedReturnType = mock(VariableData.class);
		when(mockedReturnType.isPrimitive()).thenReturn(true);

		ClassData mockedClass = mock(ClassData.class);
		ParsedMethod parsedMethod = new ParsedMethod(mockedClass, "");

		parsedMethod.addReturnType(mockedReturnType);

		assertEquals(mockedReturnType, parsedMethod.getReturnType());
		assertTrue(parsedMethod.hasPrimitiveReturnType());

		mockedReturnType = mock(VariableData.class);
		when(mockedReturnType.isPrimitive()).thenReturn(false);

		mockedClass = mock(ClassData.class);
		parsedMethod = new ParsedMethod(mockedClass, "");

		parsedMethod.addReturnType(mockedReturnType);

		assertEquals(mockedReturnType, parsedMethod.getReturnType());
		assertFalse(parsedMethod.hasPrimitiveReturnType());
	}

	@Test
	public void canAddParameter() {
		VariableData mockedParameter = mock(VariableData.class);
		when(mockedParameter.isPrimitive()).thenReturn(true);

		ClassData mockedClass = mock(ClassData.class);
		ParsedMethod parsedMethod = new ParsedMethod(mockedClass, "");

		parsedMethod.addParameter(mockedParameter);

		List<VariableData> parameters = parsedMethod.getParameters();

		assertEquals(1, parameters.size());
		assertEquals(mockedParameter, parameters.get(0));
		assertEquals(1, parsedMethod.getPrimitiveParameterCount());
	}

	@Test
	public void canAddMultipleParameters() {
		VariableData firstMockedParameter = mock(VariableData.class);
		when(firstMockedParameter.isPrimitive()).thenReturn(false);

		VariableData secondMockedParameter = mock(VariableData.class);
		when(secondMockedParameter.isPrimitive()).thenReturn(true);

		ClassData mockedClass = mock(ClassData.class);
		ParsedMethod parsedMethod = new ParsedMethod(mockedClass, "");

		parsedMethod.addParameter(firstMockedParameter);
		parsedMethod.addParameter(secondMockedParameter);

		List<VariableData> parameters = parsedMethod.getParameters();

		assertEquals(2, parameters.size());
		assertThat(parameters, containsInAnyOrder(firstMockedParameter, secondMockedParameter));
		assertEquals(1, parsedMethod.getPrimitiveParameterCount());
	}

	@Test
	public void canAddLocalVariable() {
		VariableData mockedVariable = mock(VariableData.class);
		when(mockedVariable.isPrimitive()).thenReturn(true);

		ClassData mockedClass = mock(ClassData.class);
		ParsedMethod parsedMethod = new ParsedMethod(mockedClass, "");

		parsedMethod.addVariable(mockedVariable);

		List<VariableData> localVariables = parsedMethod.getLocalVariables();

		assertEquals(1, localVariables.size());
		assertEquals(mockedVariable, localVariables.get(0));
		assertEquals(1, parsedMethod.getPrimitiveLocalCount());
	}

	@Test
	public void canAddMultipleLocalVariables() {
		VariableData firstMockedVariable = mock(VariableData.class);
		when(firstMockedVariable.isPrimitive()).thenReturn(false);

		VariableData secondMockedVariable = mock(VariableData.class);
		when(secondMockedVariable.isPrimitive()).thenReturn(true);

		ClassData mockedClass = mock(ClassData.class);
		ParsedMethod parsedMethod = new ParsedMethod(mockedClass, "");

		parsedMethod.addVariable(firstMockedVariable);
		parsedMethod.addVariable(secondMockedVariable);

		List<VariableData> localVariables = parsedMethod.getLocalVariables();

		assertEquals(2, localVariables.size());
		assertThat(localVariables, containsInAnyOrder(firstMockedVariable, secondMockedVariable));
		assertEquals(1, parsedMethod.getPrimitiveLocalCount());
	}
}
