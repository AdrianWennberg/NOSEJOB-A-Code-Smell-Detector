package com.codingrangers.nosejob.parser.data;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParsedVariableTest {

	@Test
	public void nameIsSetCorrectly() {
		String prefixName = "name.prefix.ClassName";

		String variableName = "variableName";
		String fullyQualifiedVariableName = "name.prefix.ClassName.variableName";

		ParsedVariable parsedVariable = new ParsedVariable(prefixName, variableName, "");

		assertEquals(variableName, parsedVariable.getName());
		assertEquals(fullyQualifiedVariableName, parsedVariable.getFullyQualifiedName());

		prefixName = "other.name.prefix.OtherClassName.SomeMethod";

		variableName = "otherVariableName";
		fullyQualifiedVariableName = "other.name.prefix.OtherClassName.SomeMethod.otherVariableName";

		parsedVariable = new ParsedVariable(prefixName, variableName, "");

		assertEquals(variableName, parsedVariable.getName());
		assertEquals(fullyQualifiedVariableName, parsedVariable.getFullyQualifiedName());
	}

	@Test
	public void pathSetCorrectly() {
		String path = "C:/File/path.java";

		ParsedVariable parsedVariable = new ParsedVariable("", "", path);

		assertEquals(path, parsedVariable.getFilePath());

		path = "C:/File/other/path.java";

		parsedVariable = new ParsedVariable("", "", path);

		assertEquals(path, parsedVariable.getFilePath());
	}

	@Test
	public void canSetLineNumbers() {
		int startLine = 5;
		int endLine = 100;
		int lineCount = 96;

		ParsedVariable parsedVariable = new ParsedVariable("", "", "");

		parsedVariable.setStartLine(startLine);
		parsedVariable.setEndLine(endLine);

		assertEquals(startLine, parsedVariable.getStartLineNumber());
		assertEquals(endLine, parsedVariable.getEndLineNumber());
		assertEquals(lineCount, parsedVariable.getLineCount());

		startLine = 10;
		endLine = 10;
		lineCount = 1;

		parsedVariable = new ParsedVariable("", "", "");
		parsedVariable.setStartLine(startLine);
		parsedVariable.setEndLine(endLine);

		assertEquals(startLine, parsedVariable.getStartLineNumber());
		assertEquals(endLine, parsedVariable.getEndLineNumber());
		assertEquals(lineCount, parsedVariable.getLineCount());
	}

	@Test(expected = IllegalArgumentException.class)
	public void negativeStartLine() {
		ParsedVariable parsedVariable = new ParsedVariable("", "", "");
		parsedVariable.setStartLine(-5);
	}

	@Test(expected = IllegalArgumentException.class)
	public void negativeEndLine() {
		ParsedVariable parsedVariable = new ParsedVariable("", "", "");
		parsedVariable.setEndLine(-4);
	}

	@Test(expected = IllegalArgumentException.class)
	public void startLineGreaterThanEndLine() {
		ParsedVariable parsedVariable = new ParsedVariable("", "", "");
		parsedVariable.setEndLine(5);
		parsedVariable.setStartLine(6);
	}

	@Test(expected = IllegalArgumentException.class)
	public void endLineLessThanStartLine() {
		ParsedVariable parsedVariable = new ParsedVariable("", "", "");
		parsedVariable.setStartLine(11);
		parsedVariable.setEndLine(9);
	}

	@Test(expected = IllegalStateException.class)
	public void startLineAlreadySet() {
		ParsedVariable parsedVariable = new ParsedVariable("", "", "");
		parsedVariable.setStartLine(10);
		parsedVariable.setStartLine(10);
	}

	@Test(expected = IllegalStateException.class)
	public void endLineAlreadySet() {
		ParsedVariable parsedVariable = new ParsedVariable("", "", "");
		parsedVariable.setEndLine(12);
		parsedVariable.setEndLine(12);
	}

	@Test
	public void canSetIsPrimitive() {
		ParsedVariable parsedVariable = new ParsedVariable("", "", "");

		assertFalse(parsedVariable.isPrimitive());

		parsedVariable.setIsPrimitive();

		assertTrue(parsedVariable.isPrimitive());
	}

	@Test
	public void canSetVariableType() {
		ParsedVariable parsedVariable = new ParsedVariable("", "", "");

		String variableType = "int";
		parsedVariable.setVariableType(variableType);

		assertEquals(variableType, parsedVariable.getType());

		parsedVariable = new ParsedVariable("", "", "");

		variableType = "String";
		parsedVariable.setVariableType(variableType);

		assertEquals(variableType, parsedVariable.getType());
	}
}
