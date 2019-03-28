package com.codingrangers.nosejob.models.parsing;

import com.github.javaparser.*;
import com.github.javaparser.ast.*;

import java.io.File;
import java.io.FileNotFoundException;

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
		File file = new File(path);
		
		
		CompilationUnit compilationUnit = null;
		try {
			compilationUnit = JavaParser.parse(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ParsedProject projectData = new ParsedProject();
		
		ProjectVisitor visitor = new ProjectVisitor();
		visitor.visit(compilationUnit, projectData);
		
		return projectData;
	}

}
