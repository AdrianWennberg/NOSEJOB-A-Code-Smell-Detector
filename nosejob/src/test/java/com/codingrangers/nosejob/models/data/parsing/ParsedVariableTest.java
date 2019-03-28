package com.codingrangers.nosejob.models.data.parsing;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class ParsedVariableTest {

	@Test
	public void nameIsSetCorrectly() {
		String prefixName = "name.prefix.ClassName";
		ParsedCodeUnit mockedCodeData = mock(ParsedCodeUnit.class);
		when(mockedCodeData.getFullyQualifiedName()).thenReturn(prefixName);

		String variableName = "variableName";
		String fullyQuallifiedVariableName = "name.prefix.ClassName.variableName";

		ParsedVariable parsedVariable = new ParsedVariable(mockedCodeData, variableName);

		assertEquals(variableName, parsedVariable.getName());
		assertEquals(fullyQuallifiedVariableName, parsedVariable.getFullyQualifiedName());

		prefixName = "other.name.prefix.OtherClassName.SomeMethod";
		mockedCodeData = mock(ParsedCodeUnit.class);
		when(mockedCodeData.getFullyQualifiedName()).thenReturn(prefixName);

		variableName = "otherVariableName";
		fullyQuallifiedVariableName = "other.name.prefix.OtherClassName.SomeMethod.otherVariableName";

		parsedVariable = new ParsedVariable(mockedCodeData, variableName);

		assertEquals(variableName, parsedVariable.getName());
		assertEquals(fullyQuallifiedVariableName, parsedVariable.getFullyQualifiedName());
	}

	@Test
	public void pathSetCorrectly() {
		String path = "C:/File/path.java";
		ParsedCodeUnit mockedCodeData = mock(ParsedCodeUnit.class);
		when(mockedCodeData.getFilePath()).thenReturn(path);

		ParsedVariable parsedVariable = new ParsedVariable(mockedCodeData, "");

		assertEquals(path, parsedVariable.getFilePath());

		path = "C:/File/other/path.java";
		mockedCodeData = mock(ParsedCodeUnit.class);
		when(mockedCodeData.getFilePath()).thenReturn(path);

		parsedVariable = new ParsedVariable(mockedCodeData, "");

		assertEquals(path, parsedVariable.getFilePath());
	}

	@Test
	public void canSetLineNumbers() {
		int startLine = 5;
		int endLine = 100;
		int lineCount = 96;

		ParsedCodeUnit mockedCodeData = mock(ParsedCodeUnit.class);
		ParsedVariable parsedVariable = new ParsedVariable(mockedCodeData, "");

		parsedVariable.setStartLine(startLine);
		parsedVariable.setEndLine(endLine);

		assertEquals(startLine, parsedVariable.getStartLineNumber());
		assertEquals(endLine, parsedVariable.getEndLineNumber());
		assertEquals(lineCount, parsedVariable.getLineCount());

		startLine = 10;
		endLine = 10;
		lineCount = 1;

		parsedVariable = new ParsedVariable(mockedCodeData, "");
		parsedVariable.setStartLine(startLine);
		parsedVariable.setEndLine(endLine);

		assertEquals(startLine, parsedVariable.getStartLineNumber());
		assertEquals(endLine, parsedVariable.getEndLineNumber());
		assertEquals(lineCount, parsedVariable.getLineCount());
	}

	@Test(expected = IllegalArgumentException.class)
	public void negativeStartLine() {
		ParsedCodeUnit mockedCodeData = mock(ParsedCodeUnit.class);
		ParsedVariable parsedVariable = new ParsedVariable(mockedCodeData, "");
		parsedVariable.setStartLine(-5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void negativeEndLine() {
		ParsedCodeUnit mockedCodeData = mock(ParsedCodeUnit.class);
		ParsedVariable parsedVariable = new ParsedVariable(mockedCodeData, "");
		parsedVariable.setEndLine(-4);
	}

	@Test(expected = IllegalArgumentException.class)
	public void startLineGreaterThanEndLine() {
		ParsedCodeUnit mockedCodeData = mock(ParsedCodeUnit.class);
		ParsedVariable parsedVariable = new ParsedVariable(mockedCodeData, "");
		parsedVariable.setEndLine(5);
		parsedVariable.setStartLine(6);
	}

	@Test(expected = IllegalArgumentException.class)
	public void endLineLessThanstartLine() {
		ParsedCodeUnit mockedCodeData = mock(ParsedCodeUnit.class);
		ParsedVariable parsedVariable = new ParsedVariable(mockedCodeData, "");
		parsedVariable.setStartLine(11);
		parsedVariable.setEndLine(9);
	}

	@Test(expected = IllegalStateException.class)
	public void startLineAllreadySet() {
		ParsedCodeUnit mockedCodeData = mock(ParsedCodeUnit.class);
		ParsedVariable parsedVariable = new ParsedVariable(mockedCodeData, "");
		parsedVariable.setStartLine(10);
		parsedVariable.setStartLine(10);
	}

	@Test(expected = IllegalStateException.class)
	public void endLineAllreadySet() {
		ParsedCodeUnit mockedCodeData = mock(ParsedCodeUnit.class);
		ParsedVariable parsedVariable = new ParsedVariable(mockedCodeData, "");
		parsedVariable.setEndLine(12);
		parsedVariable.setEndLine(12);
	}

	@Test
	public void canSetIsPrimitive() {
		ParsedCodeUnit mockedCodeData = mock(ParsedCodeUnit.class);
		ParsedVariable parsedVariable = new ParsedVariable(mockedCodeData, "");

		assertFalse(parsedVariable.isPrimitive());

		parsedVariable.setIsPrimitive();

		assertTrue(parsedVariable.isPrimitive());
	}

	@Test
	public void canSetVariableType() {
		ParsedCodeUnit mockedCodeData = mock(ParsedCodeUnit.class);
		ParsedVariable parsedVariable = new ParsedVariable(mockedCodeData, "");

		String variableType = "int";
		parsedVariable.setVariableType(variableType);

		assertEquals(variableType, parsedVariable.getType());

		parsedVariable = new ParsedVariable(mockedCodeData, "");

		variableType = "String";
		parsedVariable.setVariableType(variableType);

		assertEquals(variableType, parsedVariable.getType());
	}
}
