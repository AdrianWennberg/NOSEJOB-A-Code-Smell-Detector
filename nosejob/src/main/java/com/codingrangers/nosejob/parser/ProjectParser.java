package com.codingrangers.nosejob.parser;

import com.codingrangers.nosejob.models.CodeParser;
import com.codingrangers.nosejob.models.ProjectData;
import com.codingrangers.nosejob.parser.data.ParsedClass;
import com.codingrangers.nosejob.parser.data.ParsedProject;
import com.codingrangers.nosejob.parser.visitors.ClassVisitor;
import com.codingrangers.nosejob.parser.visitors.MethodVisitor;
import com.codingrangers.nosejob.parser.visitors.VariableVisitor;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ast.CompilationUnit;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;

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
	ClassVisitor ClassVisitor;

	public ProjectParser() {
		VariableVisitor variableVisitor = new VariableVisitor();
		MethodVisitor methodVisitor = new MethodVisitor(variableVisitor);
		ClassVisitor classVisitor = new ClassVisitor(methodVisitor, variableVisitor);
		ParsedProject parsedProject = new ParsedProject();
	}

	/**
	 * constructor for injecting dependencies, needed for testing perposes
	 *
	 * @param injectedProjectData
	 * @param injectedClassVisitor
	 */
	public ProjectParser(ParsedProject injectedProjectData, ClassVisitor injectedClassVisitor) {
		parsedProject = injectedProjectData;
		ClassVisitor = injectedClassVisitor;
	}

	/**
	 * @param path is taken as root of the project, all files beneath it are parse
	 */
	public ProjectData parseProject(String path) throws FileNotFoundException {
		File root = new File(path);
		if (root.exists() == false) {
			throw new FileNotFoundException();
		}

		directoryOrFile(root);

		ParsedProject returnValue = parsedProject;
		parsedProject = new ParsedProject();

		return returnValue;
	}

	private void directoryOrFile(File file) {
		if (file.isDirectory())
			parseDirectory(file);
		else if (file.isFile() && file.getName().endsWith(".java"))
			parseFile(file);
	}

	private void parseDirectory(File Directory) {
		for (File file : Directory.listFiles())
			directoryOrFile(file);
	}

	private void parseFile(File file) {
		CompilationUnit compilationUnit = null;

		try {
			compilationUnit = JavaParser.parse(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		ParsedClass parsedClass = parsedProject.createClass("packageNameGoesHere", file.getName(), file.getPath());
		ClassVisitor.visit(compilationUnit, parsedClass);
	}

}
