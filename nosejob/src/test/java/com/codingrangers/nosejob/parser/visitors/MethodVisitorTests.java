package com.codingrangers.nosejob.parser.visitors;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.codingrangers.nosejob.parser.data.ParsedMethod;
import com.codingrangers.nosejob.parser.data.ParsedVariable;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

public class MethodVisitorTests {
	
	MethodVisitor visitor;
	CompilationUnit compUnit;
	ParsedMethod methodData;
	
	@Before
	public void before() {
		visitor = new MethodVisitor(Mockito.mock(VariableVisitor.class));
		methodData = Mockito.mock(ParsedMethod.class);
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
		setCompUnit("src/test/ParserTestTargets/MethodIdentificationTestTarget.java");
		
		visitor.visit(compUnit, methodData);
		
		verify(methodData, times(2)).setReturnType("void", false);
		verify(methodData).setReturnType("int", true);
		verify(methodData).setReturnType("MethodIdentificationTestTarget", false);

		
	}
}
