package com.codingrangers.nosejob.parser;

import com.github.javaparser.*;
import com.github.javaparser.ast.*;
import com.codingrangers.nosejob.models.ProjectData;
import com.codingrangers.nosejob.models.CodeParser;
import org.springframework.stereotype.Component;

/**
 * entry point into code parsing system
 *
 * @author peter
 *
 */

@Component
public class ProjectParser implements CodeParser {

	/**
	 * @param path is taken as root of the project to be parsed, all files beneath
	 *             it are parse
	 */

	public ProjectParser getProjectParser() {
		return new ProjectParser();
	}

	public ProjectData parseProject(String path) {
		CompilationUnit compilationUnit = JavaParser.parse(path);
		ProjectData projectData = null;

		ProjectVisitor visitor = new ProjectVisitor();
		visitor.visit(compilationUnit, projectData);

		return projectData;
	}

}
