package com.codingrangers.nosejob.models.data.parsing;

import static org.junit.Assert.assertEquals;

import com.codingrangers.nosejob.models.data.CodeData;
import com.codingrangers.nosejob.models.data.MethodData;

import org.junit.Test;
import org.mockito.Mock;
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
	public void canAddMethods() {
		MethodData mockedData = mock(MethodData.class);
		ParsedClass parsedClass = new ParsedClass("", "", "");
		
	}
}