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
<<<<<<< HEAD

	VariableVisitor visitor;
	CompilationUnit compUnit;
	ParsedVariable variableData;

=======
	
	VariableVisitor visitor;
	CompilationUnit compUnit;
	ParsedVariable variableData;
	
>>>>>>> Solved conflicts
	@Before
	public void before() {
		visitor = new VariableVisitor();
		variableData = Mockito.mock(ParsedVariable.class);
	}
<<<<<<< HEAD

=======
	
>>>>>>> Solved conflicts
	void setCompUnit(String fileName) {
		File file = new File(fileName);
		try {
			compUnit = JavaParser.parse(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
<<<<<<< HEAD

	@Test
	public void identificationTest() {
		setCompUnit("src/test/ParserTestTargets/VariableIdentificationTestTarget.java");

		visitor.visit(compUnit, variableData);

=======
	
	@Test
	public void identificationTest() {
		setCompUnit("src/test/ParserTestTargets/VariableIdentificationTestTarget.java");
		
		visitor.visit(compUnit, variableData);
		
>>>>>>> Solved conflicts
		verify(variableData, times(2)).setVariableType("int");
		verify(variableData).setVariableType("String");
		verify(variableData).setVariableType("VariableIdentificationTestTarget");

	}
<<<<<<< HEAD


}
=======
	
	
}
>>>>>>> Solved conflicts
