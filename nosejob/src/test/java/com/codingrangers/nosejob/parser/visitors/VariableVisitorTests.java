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
<<<<<<< HEAD

	VariableVisitor visitor;
	CompilationUnit compUnit;
	ParsedVariable variableData;

=======
	
	VariableVisitor visitor;
	CompilationUnit compUnit;
	ParsedVariable variableData;
	
>>>>>>> Solved conflicts
=======

	VariableVisitor visitor;
	CompilationUnit compUnit;
	ParsedVariable variableData;

>>>>>>> Solved more conflicts
	@Before
	public void before() {
		visitor = new VariableVisitor();
		variableData = Mockito.mock(ParsedVariable.class);
	}
<<<<<<< HEAD
<<<<<<< HEAD

=======
	
>>>>>>> Solved conflicts
=======

>>>>>>> Solved more conflicts
	void setCompUnit(String fileName) {
		File file = new File(fileName);
		try {
			compUnit = JavaParser.parse(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
<<<<<<< HEAD
<<<<<<< HEAD

	@Test
	public void identificationTest() {
		setCompUnit("src/test/ParserTestTargets/VariableIdentificationTestTarget.java");

		visitor.visit(compUnit, variableData);

=======
	
=======

>>>>>>> Solved more conflicts
	@Test
	public void identificationTest() {
		setCompUnit("src/test/ParserTestTargets/VariableIdentificationTestTarget.java");

		visitor.visit(compUnit, variableData);
<<<<<<< HEAD
		
>>>>>>> Solved conflicts
=======

>>>>>>> Solved more conflicts
		verify(variableData, times(2)).setVariableType("int");
		verify(variableData).setVariableType("String");
		verify(variableData).setVariableType("VariableIdentificationTestTarget");

	}
<<<<<<< HEAD
<<<<<<< HEAD


}
=======
	
	
}
>>>>>>> Solved conflicts
=======


}
>>>>>>> Solved more conflicts
