package com.codingrangers.nosejob.parser.visitors;

import static org.mockito.Mockito.*;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.*;
import org.mockito.Mockito;

import com.codingrangers.nosejob.parser.data.ParsedClass;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

public class ClassVisitorTests {

    ClassVisitor visitor;
    ParsedClass ClassData;

    @Before
    public void before() {
        visitor = new ClassVisitor(Mockito.mock(MethodVisitor.class),Mockito.mock(VariableVisitor.class));
        ClassData = Mockito.mock(ParsedClass.class);
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
        ReflectionTypeSolver refelctionSolver = new ReflectionTypeSolver();

        CombinedTypeSolver typeSolver = new CombinedTypeSolver(javaParserSolver,refelctionSolver);

        JavaSymbolSolver symbolSolver = new JavaSymbolSolver(typeSolver);
        JavaParser.getStaticConfiguration().setSymbolResolver(symbolSolver);


        CompilationUnit compUnit[] = {getCompUnit("src/test/ParserTestTargets/ReferenceTestTargets/referenceTests/ReferenceTest1.java")
                ,getCompUnit("src/test/ParserTestTargets/ReferenceTestTargets/referenceTests/ReferenceTest2.java")
                ,getCompUnit("src/test/ParserTestTargets/ReferenceTestTargets/referenceTests/ReferenceTest3.java")};

        visitor.visit(compUnit[0], ClassData);
        visitor.visit(compUnit[1], ClassData);
        visitor.visit(compUnit[2], ClassData);

        verify(ClassData).addReferenceToMethod("referenceTests.ReferenceTest1", "staticMethodToCall()");
        verify(ClassData).addReferenceToField("referenceTests.ReferenceTest3", "test");

        verify(ClassData,never()).addReferenceToMethod("referenceTests.ReferenceTest1", "methodToCall()");
        verify(ClassData,never()).addReferenceToField("referenceTests.ReferenceTest1", "field");
    }
}