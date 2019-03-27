package com.codingrangers.nosejob.models.parsing;

import com.github.javaparser.*;
import com.github.javaparser.ast.*;
import com.codingrangers.nosejob.models.data.ProjectData;
import com.codingrangers.nosejob.models.data.parsing.*;

/**
 * entry point into code parsing system
 * @author peter
 *
 */
public class ProjectParser implements CodeParser {

	/**
	 * @param path is taken as root of the project to be parsed,
	 * 			all files beneath it are parse
	 */
	public ProjectData parseProject(String path) {
		CompilationUnit compilationUnit = JavaParser.parse(path);
		ParsedProject projectData = null;
		
		ProjectVisitor visitor = new ProjectVisitor();
		visitor.visit(compilationUnit, projectData);
		
		return projectData;
	}

}
