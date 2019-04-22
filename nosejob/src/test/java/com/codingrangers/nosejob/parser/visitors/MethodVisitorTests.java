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
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

public class MethodVisitorTests {
<<<<<<< HEAD
<<<<<<< HEAD

	MethodVisitor visitor;
	ParsedMethod methodData;

=======
	
	MethodVisitor visitor;
	ParsedMethod methodData;
	
>>>>>>> Solved conflicts
=======

	MethodVisitor visitor;
	ParsedMethod methodData;

>>>>>>> Solved more conflicts
	@Before
	public void before() {
		visitor = new MethodVisitor(Mockito.mock(VariableVisitor.class));
		methodData = Mockito.mock(ParsedMethod.class);
	}
<<<<<<< HEAD
<<<<<<< HEAD

	CompilationUnit getCompUnit(String fileName) {
=======
	
	CompilationUnit getCompUnit(String fileName) {	
>>>>>>> Solved conflicts
=======

	CompilationUnit getCompUnit(String fileName) {
>>>>>>> Solved more conflicts
		CompilationUnit compUnit = null;

		File file = new File(fileName);
		try {
			compUnit = JavaParser.parse(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
<<<<<<< HEAD
<<<<<<< HEAD

		return compUnit;
	}

	@Test
	public void identificationTest() {
		CompilationUnit compUnit = getCompUnit("src/test/ParserTestTargets/MethodIdentificationTestTarget.java");

		visitor.visit(compUnit, methodData);

=======
		
=======

>>>>>>> Solved more conflicts
		return compUnit;
	}

	@Test
	public void identificationTest() {
		CompilationUnit compUnit = getCompUnit("src/test/ParserTestTargets/MethodIdentificationTestTarget.java");

		visitor.visit(compUnit, methodData);
<<<<<<< HEAD
		
>>>>>>> Solved conflicts
=======

>>>>>>> Solved more conflicts
		verify(methodData, times(2)).setReturnType("void", false);
		verify(methodData).setReturnType("int", true);
		verify(methodData).setReturnType("MethodIdentificationTestTarget", false);
	}
<<<<<<< HEAD
<<<<<<< HEAD

	@Test
	public void referenceTests() {
		File referenceTestFile = new File("src/test/ParserTestTargets/ReferenceTestTargets");
		JavaParserTypeSolver javaParserSolver = new JavaParserTypeSolver(referenceTestFile);
		ReflectionTypeSolver refelctionSolver = new ReflectionTypeSolver();

		CombinedTypeSolver typeSolver = new CombinedTypeSolver(javaParserSolver,refelctionSolver);

		JavaSymbolSolver symbolSolver = new JavaSymbolSolver(typeSolver);
		JavaParser.getStaticConfiguration().setSymbolResolver(symbolSolver);


=======
	
=======

>>>>>>> Solved more conflicts
	@Test
	public void referenceTests() {
		File referenceTestFile = new File("src/test/ParserTestTargets/ReferenceTestTargets");
		JavaParserTypeSolver javaParserSolver = new JavaParserTypeSolver(referenceTestFile);
		ReflectionTypeSolver refelctionSolver = new ReflectionTypeSolver();

		CombinedTypeSolver typeSolver = new CombinedTypeSolver(javaParserSolver,refelctionSolver);

		JavaSymbolSolver symbolSolver = new JavaSymbolSolver(typeSolver);
		JavaParser.getStaticConfiguration().setSymbolResolver(symbolSolver);
<<<<<<< HEAD
			
		
>>>>>>> Solved conflicts
=======


>>>>>>> Solved more conflicts
		CompilationUnit compUnit[] = {getCompUnit("src/test/ParserTestTargets/ReferenceTestTargets/referenceTests/ReferenceTest1.java")
				,getCompUnit("src/test/ParserTestTargets/ReferenceTestTargets/referenceTests/ReferenceTest2.java")
				,getCompUnit("src/test/ParserTestTargets/ReferenceTestTargets/referenceTests/ReferenceTest3.java")};

<<<<<<< HEAD
<<<<<<< HEAD

		visitor.visit(compUnit[0], methodData);
=======
				
		visitor.visit(compUnit[0], methodData);	
>>>>>>> Solved conflicts
=======

		visitor.visit(compUnit[0], methodData);
>>>>>>> Solved more conflicts
		visitor.visit(compUnit[1], methodData);
		visitor.visit(compUnit[2], methodData);

		verify(methodData,times(3)).addReferenceToMethod("referenceTests.ReferenceTest1", "methodToCall()");
		verify(methodData,times(2)).addReferenceToField("referenceTests.ReferenceTest1", "field");
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
