package com.codingrangers.nosejob.models.parsing;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.*;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;

public class ProjectParserTests {

	@Test
	public void parseProjectTest() {
		CodeParser parser = new ProjectParser();

		parser.parseProject("src/test/java/com/codingrangers/nosejob/models/parsing/testTargets/VariableIdentificationTarget.java");
	}
	
}
