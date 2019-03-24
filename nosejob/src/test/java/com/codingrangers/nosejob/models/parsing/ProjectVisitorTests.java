package com.codingrangers.nosejob.models.parsing;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.*;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

public class ProjectVisitorTests {

	@Test
	public void flowTest() {
		ProjectVisitor visitor = new ProjectVisitor();
		File file = new File("src/test/java/com/codingrangers/nosejob/models/parsing/testTargets/VariableIdentificationTarget.java");
		CompilationUnit cu = null;
		try {
			cu = JavaParser.parse(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		visitor.visit(cu, null);
	}
	
}
