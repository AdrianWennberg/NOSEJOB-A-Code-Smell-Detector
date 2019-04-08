package com.codingrangers.nosejob.parser;

import com.codingrangers.nosejob.models.IProjectData;
import com.github.javaparser.*;
import com.github.javaparser.ast.*;
import com.codingrangers.nosejob.models.ICodeParser;
import org.springframework.stereotype.Component;

/**
 * entry point into code parsing system
 *
 * @author peter
 *
 */

@Component
public class ProjectParser implements ICodeParser {

	/**
	 * @param path is taken as root of the project to be parsed, all files beneath
	 *             it are parse
	 */

	public ProjectParser getProjectParser() {
		return new ProjectParser();
	}

	public IProjectData parseProject(String path) {
		CompilationUnit compilationUnit = JavaParser.parse(path);
		IProjectData IProjectData = null;

		ProjectVisitor visitor = new ProjectVisitor();
		visitor.visit(compilationUnit, IProjectData);

		return IProjectData;
	}

}
