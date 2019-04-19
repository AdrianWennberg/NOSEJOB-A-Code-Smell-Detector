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
	
	MethodVisitor visitor;
	ParsedMethod methodData;
	
	@Before
	public void before() {
		visitor = new MethodVisitor(Mockito.mock(VariableVisitor.class));
		methodData = Mockito.mock(ParsedMethod.class);
	}
	
	CompilationUnit getCompUnit(String fileName) {	
		CompilationUnit compUnit = null;

		File file = new File(fileName);
		try {
			compUnit = JavaParser.parse(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return compUnit;
	}
	
	@Test
	public void identificationTest() {
		CompilationUnit compUnit = getCompUnit("src/test/ParserTestTargets/MethodIdentificationTestTarget.java");
		
		visitor.visit(compUnit, methodData);
		
		verify(methodData, times(2)).setReturnType("void", false);
		verify(methodData).setReturnType("int", true);
		verify(methodData).setReturnType("MethodIdentificationTestTarget", false);
	}
	
	@Test
	public void referenceTests() {		
		File referenceTestFile = new File("src/test/ParserTestTargets/ReferenceTestTargets");
		JavaParserTypeSolver javaParserSolver = new JavaParserTypeSolver(referenceTestFile);
		ReflectionTypeSolver refelctionSolver = new ReflectionTypeSolver();
		
		CombinedTypeSolver typeSolver = new CombinedTypeSolver(javaParserSolver,refelctionSolver);
		
		JavaSymbolSolver symbolSolver = new JavaSymbolSolver(typeSolver);
		JavaParser.getStaticConfiguration().setSymbolResolver(symbolSolver);
			
		
		CompilationUnit compUnit[] = {getCompUnit("src/test/ParserTestTargets/ReferenceTestTargets/referenceTests/ReferenceTest1.java")
				,getCompUnit("src/test/ParserTestTargets/ReferenceTestTargets/referenceTests/ReferenceTest2.java")
				};
				
		visitor.visit(compUnit[0], methodData);	
		visitor.visit(compUnit[1], methodData);
		
		verify(methodData,times(3)).addReferenceToMethod("referenceTests.ReferenceTest1", "methodToCall()");
		verify(methodData,times(2)).addReferenceToField("referenceTests.ReferenceTest1", "field");
	}
}
