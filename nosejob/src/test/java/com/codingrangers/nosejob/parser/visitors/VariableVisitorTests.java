package com.codingrangers.nosejob.parser.visitors;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.*;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

import com.codingrangers.nosejob.parser.data.ParsedVariable;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.VariableDeclarator;



public class VariableVisitorTests {

	VariableVisitor visitor;
	CompilationUnit compUnit;
	ParsedVariable variableData;

	@Before
	public void before() {
		visitor = new VariableVisitor();
		variableData = Mockito.mock(ParsedVariable.class);
	}

	void setCompUnit(String fileName) {
		File file = new File(fileName);
		try {
			compUnit = JavaParser.parse(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void identificationTest() {
		setCompUnit("src/test/ParserTestTargets/VariableIdentificationTestTarget.java");

		visitor.visit(compUnit, variableData);

		verify(variableData, times(2)).setVariableType("int");
		verify(variableData).setVariableType("String");
		verify(variableData).setVariableType("VariableIdentificationTestTarget");

	}


}

