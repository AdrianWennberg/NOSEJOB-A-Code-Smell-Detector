package com.codingrangers.nosejob.parser;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import com.codingrangers.nosejob.models.IClassData;
import org.junit.Test;

import com.codingrangers.nosejob.models.ICodeData;
import com.codingrangers.nosejob.models.IVariableData;

public class ParsedMethodTest {
	@Test
	public void nameIsSetCorrectly() {
		String fullyQualifiedClassName = "name.prefix.ClassName";
		IClassData mockedClass = mock(IClassData.class);
		when(mockedClass.getFullyQualifiedName()).thenReturn(fullyQualifiedClassName);

		String methodSignature = "methodName(int)";
		String fullyQuallifiedMethodName = "name.prefix.ClassName.methodName(int)";

		ICodeData parsedMethod = new ParsedMethod(mockedClass, methodSignature);

		assertEquals(methodSignature, parsedMethod.getName());
		assertEquals(fullyQuallifiedMethodName, parsedMethod.getFullyQualifiedName());

		fullyQualifiedClassName = "other.name.prefix.OtherClassName";
		mockedClass = mock(IClassData.class);
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
		IClassData mockedClass = mock(IClassData.class);
		when(mockedClass.getFilePath()).thenReturn(path);

		ICodeData parsedMethod = new ParsedMethod(mockedClass, "");

		assertEquals(path, parsedMethod.getFilePath());

		path = "C:/File/other/path.java";
		mockedClass = mock(IClassData.class);
		when(mockedClass.getFilePath()).thenReturn(path);

		parsedMethod = new ParsedMethod(mockedClass, "");

		assertEquals(path, parsedMethod.getFilePath());
	}

	@Test
	public void canSetLineNumbers() {
		int startLine = 5;
		int endLine = 100;
		int lineCount = 96;

		IClassData mockedClass = mock(IClassData.class);
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
		IClassData mockedClass = mock(IClassData.class);
		ParsedMethod parsedMethod = new ParsedMethod(mockedClass, "");
		parsedMethod.setStartLine(-5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void negativeEndLine() {
		IClassData mockedClass = mock(IClassData.class);
		ParsedMethod parsedMethod = new ParsedMethod(mockedClass, "");
		parsedMethod.setEndLine(-4);
	}

	@Test(expected = IllegalArgumentException.class)
	public void startLineGreaterThanEndLine() {
		IClassData mockedClass = mock(IClassData.class);
		ParsedMethod parsedMethod = new ParsedMethod(mockedClass, "");
		parsedMethod.setEndLine(5);
		parsedMethod.setStartLine(6);
	}

	@Test(expected = IllegalArgumentException.class)
	public void endLineLessThanstartLine() {
		IClassData mockedClass = mock(IClassData.class);
		ParsedMethod parsedMethod = new ParsedMethod(mockedClass, "");
		parsedMethod.setStartLine(10);
		parsedMethod.setEndLine(9);
	}

	@Test(expected = IllegalStateException.class)
	public void startLineAllreadySet() {
		IClassData mockedClass = mock(IClassData.class);
		ParsedMethod parsedMethod = new ParsedMethod(mockedClass, "");
		parsedMethod.setStartLine(10);
		parsedMethod.setStartLine(10);
	}

	@Test(expected = IllegalStateException.class)
	public void endLineAllreadySet() {
		IClassData mockedClass = mock(IClassData.class);
		ParsedMethod parsedMethod = new ParsedMethod(mockedClass, "");
		parsedMethod.setEndLine(12);
		parsedMethod.setEndLine(12);
	}

	@Test
	public void canGetClassName() {
		String className = "testClass";
		IClassData mockedClass = mock(IClassData.class);
		when(mockedClass.getName()).thenReturn(className);

		ParsedMethod parsedMethod = new ParsedMethod(mockedClass, "");

		assertEquals(className, parsedMethod.getClassName());
	}

	@Test
	public void canAddReturnType() {
		IVariableData mockedReturnType = mock(IVariableData.class);
		when(mockedReturnType.isPrimitive()).thenReturn(true);

		IClassData mockedClass = mock(IClassData.class);
		ParsedMethod parsedMethod = new ParsedMethod(mockedClass, "");

		parsedMethod.addReturnType(mockedReturnType);

		assertEquals(mockedReturnType, parsedMethod.getReturnType());
		assertTrue(parsedMethod.hasPrimitiveReturnType());

		mockedReturnType = mock(IVariableData.class);
		when(mockedReturnType.isPrimitive()).thenReturn(false);

		mockedClass = mock(IClassData.class);
		parsedMethod = new ParsedMethod(mockedClass, "");

		parsedMethod.addReturnType(mockedReturnType);

		assertEquals(mockedReturnType, parsedMethod.getReturnType());
		assertFalse(parsedMethod.hasPrimitiveReturnType());
	}

	@Test
	public void canAddParameter() {
		IVariableData mockedParameter = mock(IVariableData.class);
		when(mockedParameter.isPrimitive()).thenReturn(true);

		IClassData mockedClass = mock(IClassData.class);
		ParsedMethod parsedMethod = new ParsedMethod(mockedClass, "");

		parsedMethod.addParameter(mockedParameter);

		List<IVariableData> parameters = parsedMethod.getParameters();

		assertEquals(1, parameters.size());
		assertEquals(mockedParameter, parameters.get(0));
		assertEquals(1, parsedMethod.getPrimitiveParameterCount());
	}

	@Test
	public void canAddMultipleParameters() {
		IVariableData firstMockedParameter = mock(IVariableData.class);
		when(firstMockedParameter.isPrimitive()).thenReturn(false);

		IVariableData secondMockedParameter = mock(IVariableData.class);
		when(secondMockedParameter.isPrimitive()).thenReturn(true);

		IClassData mockedClass = mock(IClassData.class);
		ParsedMethod parsedMethod = new ParsedMethod(mockedClass, "");

		parsedMethod.addParameter(firstMockedParameter);
		parsedMethod.addParameter(secondMockedParameter);

		List<IVariableData> parameters = parsedMethod.getParameters();

		assertEquals(2, parameters.size());
		assertThat(parameters, containsInAnyOrder(firstMockedParameter, secondMockedParameter));
		assertEquals(1, parsedMethod.getPrimitiveParameterCount());
	}

	@Test
	public void canAddLocalVariable() {
		IVariableData mockedVariable = mock(IVariableData.class);
		when(mockedVariable.isPrimitive()).thenReturn(true);

		IClassData mockedClass = mock(IClassData.class);
		ParsedMethod parsedMethod = new ParsedMethod(mockedClass, "");

		parsedMethod.addVariable(mockedVariable);

		List<IVariableData> localVariables = parsedMethod.getLocalVariables();

		assertEquals(1, localVariables.size());
		assertEquals(mockedVariable, localVariables.get(0));
		assertEquals(1, parsedMethod.getPrimitiveLocalCount());
	}

	@Test
	public void canAddMultipleLocalVariables() {
		IVariableData firstMockedVariable = mock(IVariableData.class);
		when(firstMockedVariable.isPrimitive()).thenReturn(false);

		IVariableData secondMockedVariable = mock(IVariableData.class);
		when(secondMockedVariable.isPrimitive()).thenReturn(true);

		IClassData mockedClass = mock(IClassData.class);
		ParsedMethod parsedMethod = new ParsedMethod(mockedClass, "");

		parsedMethod.addVariable(firstMockedVariable);
		parsedMethod.addVariable(secondMockedVariable);

		List<IVariableData> localVariables = parsedMethod.getLocalVariables();

		assertEquals(2, localVariables.size());
		assertThat(localVariables, containsInAnyOrder(firstMockedVariable, secondMockedVariable));
		assertEquals(1, parsedMethod.getPrimitiveLocalCount());
	}
}
