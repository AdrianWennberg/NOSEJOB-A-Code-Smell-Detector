package com.codingrangers.nosejob.parser;

import com.codingrangers.nosejob.models.ProjectData;
import com.github.javaparser.*;
import com.github.javaparser.ast.*;
import com.codingrangers.nosejob.parser.visitors.*;
import com.codingrangers.nosejob.models.CodeParser;

import java.io.File;
import java.io.FileNotFoundException;

import org.springframework.stereotype.Component;

/**
 * entry point into code parsing system Each instance can parse one project at a
 * time, thus is not thread safe
 *
 * @author peter
 *
 */

@Component
public class ProjectParser implements CodeParser {

	ParsedProject parsedProject;
	ClassVisitor classVisitor;

	public ProjectParser() {
		VariableVisitor variableVisitor = new VariableVisitor();
		MethodVisitor methodVisitor = new MethodVisitor(variableVisitor);
		this.classVisitor = new ClassVisitor(methodVisitor, variableVisitor);
		this.parsedProject = new ParsedProject();
	}

	/**
	 * constructor for injecting dependencies, needed for testing perposes
	 *
	 * @param injectedProjectData
	 * @param injectedClassVisitor
	 */
	public ProjectParser(ParsedProject injectedProjectData, ClassVisitor injectedClassVisitor) {
		parsedProject = injectedProjectData;
		classVisitor = injectedClassVisitor;
	}

	/**
	 * @param path is taken as root of the project, all files beneath it are parse
	 */
	public ProjectData parseProject(String path) throws FileNotFoundException {
		File root = new File(path);
		if (!root.exists()) {
			throw new FileNotFoundException();
		}

		resolveParse(root);

		ParsedProject returnValue = parsedProject;
		parsedProject = new ParsedProject();

		return returnValue;
	}

	private void resolveParse(File file) {
		if (file.isDirectory()) {
			parseDirectory(file);
		} else if (file.isFile() && file.getName().endsWith(".java")) {
			parseFile(file);
		}
	}

	private void parseDirectory(File Directory) {
		for (File file : Directory.listFiles()) {
			resolveParse(file);
		}
	}

	private void parseFile(File file) {
		CompilationUnit compilationUnit = null;

		try {
			compilationUnit = JavaParser.parse(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		ParsedClass parsedClass = new ParsedClass("packageNameGoesHere", file.getName(), file.getPath());
		classVisitor.visit(compilationUnit, parsedClass);
		parsedProject.addClass(parsedClass);
	}

}
