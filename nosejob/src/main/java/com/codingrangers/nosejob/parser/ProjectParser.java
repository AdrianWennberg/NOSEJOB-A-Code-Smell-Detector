package com.codingrangers.nosejob.parser;

import com.github.javaparser.*;
import com.github.javaparser.ast.*;
import com.codingrangers.nosejob.models.ProjectData;
import com.codingrangers.nosejob.parser.visitors.ClassVisitor;
import com.codingrangers.nosejob.parser.visitors.ProjectVisitor;
import com.codingrangers.nosejob.models.CodeParser;

import java.io.File;
import java.io.FileNotFoundException;

import org.springframework.stereotype.Component;

/**
 * entry point into code parsing system
 *
 * @author peter
 *
 */

@Component
public class ProjectParser implements CodeParser {

	ParsedProject parsedProject;
	ClassVisitor visitor;

	public ProjectParser() {
		parsedProject = new ParsedProject();
		visitor = new ClassVisitor();
	}
	
	/**
	 * constructor for injecting dependencies, needed for testing perposes
	 * @param injectedProjectData
	 * @param injectedClassVisitor
	 */
	public ProjectParser(ParsedProject injectedProjectData,ClassVisitor injectedClassVisitor) {
		parsedProject = injectedProjectData;
		visitor = injectedClassVisitor;
	}
	
	/**
	 * @param path is taken as root of the project to be parsed, all files beneath
	 *             it are parse
	 */
	public ProjectData parseProject(String path) throws ParseFailedException{
		File root = new File(path);
		if(root.exists() == false) 
			throw new ParseFailedException("Could not open file with path:" + path);
				
		directoryOrFile(root);
		
		return parsedProject;
	}
	
	private void directoryOrFile(File file) {
		if(file.isDirectory())
			parseDirectory(file);
		else if(file.isFile() && file.getName().endsWith(".java"))
			parseFile(file);
		
	}

	private void parseDirectory(File Directory) {
		for(File file : Directory.listFiles()) 
			directoryOrFile(file);
	}
	
	private void parseFile(File file) {
		CompilationUnit compilationUnit = null;
		
		try {
			compilationUnit = JavaParser.parse(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		ParsedClass parsedClass = new ParsedClass("packageNameGoesHere", file.getName(), file.getPath());
		visitor.visit(compilationUnit, parsedClass);
		parsedProject.addClass(parsedClass);
	}
	
}
