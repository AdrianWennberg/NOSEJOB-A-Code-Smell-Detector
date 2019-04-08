package com.codingrangers.nosejob.parser;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.*;

import com.codingrangers.nosejob.parser.visitors.ClassVisitor;
import com.codingrangers.nosejob.parser.visitors.MethodVisitor;
import com.codingrangers.nosejob.parser.visitors.VariableVisitor;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

public class ParserIntegratedFlowTests {

	@Test
	public void FlowTest() {
		File file = new File("src/test/ParserTestTargets/VariableIdentificationTestTarget.java");
		CompilationUnit cu = null;
		try {
			cu = JavaParser.parse(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		VariableVisitor variableVisitor = new VariableVisitor();
		MethodVisitor methodVisitor = new MethodVisitor(variableVisitor);
		ClassVisitor classVisitor = new ClassVisitor(methodVisitor, variableVisitor);
		classVisitor.visit(cu, new ParsedClass("","",""));
	}
	
}
