package com.codingrangers.nosejob.parser.visitors;

import com.codingrangers.nosejob.parser.data.ParsedClass;
import com.codingrangers.nosejob.parser.data.ParsedMethod;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.mockito.Mockito.*;

public class ClassVisitorTests {

	ClassVisitor visitor;
    ParsedClass classData;

	@Before
	public void before() {
        visitor = new ClassVisitor(mock(MethodVisitor.class), mock(VariableVisitor.class));
        classData = mock(ParsedClass.class);

        when(classData.createMethod(anyString())).thenReturn(mock(ParsedMethod.class));
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
	public void referenceTests() {
		File referenceTestFile = new File("src/test/ParserTestTargets/ReferenceTestTargets");
		JavaParserTypeSolver javaParserSolver = new JavaParserTypeSolver(referenceTestFile);
        ReflectionTypeSolver refectionSolver = new ReflectionTypeSolver();

        CombinedTypeSolver typeSolver = new CombinedTypeSolver(javaParserSolver, refectionSolver);

		JavaSymbolSolver symbolSolver = new JavaSymbolSolver(typeSolver);
		JavaParser.getStaticConfiguration().setSymbolResolver(symbolSolver);


        CompilationUnit[] compUnit = {getCompUnit("src/test/ParserTestTargets/ReferenceTestTargets/referenceTests/ReferenceTest1.java")
                , getCompUnit("src/test/ParserTestTargets/ReferenceTestTargets/referenceTests/ReferenceTest2.java")
                , getCompUnit("src/test/ParserTestTargets/ReferenceTestTargets/referenceTests/ReferenceTest3.java")};

		when(classData.getName()).thenReturn("ReferenceTest1");
        visitor.visit(compUnit[0], classData);
		when(classData.getName()).thenReturn("ReferenceTest2");
        visitor.visit(compUnit[1], classData);
		when(classData.getName()).thenReturn("ReferenceTest3");
        visitor.visit(compUnit[2], classData);

        verify(classData).addReferenceToMethod("referenceTests.ReferenceTest1", "staticMethodToCall()");
        verify(classData).addReferenceToField("referenceTests.ReferenceTest3", "test");

        verify(classData, never()).addReferenceToMethod("referenceTests.ReferenceTest1", "methodToCall()");
        verify(classData, never()).addReferenceToField("referenceTests.ReferenceTest1", "field");
	}
}
