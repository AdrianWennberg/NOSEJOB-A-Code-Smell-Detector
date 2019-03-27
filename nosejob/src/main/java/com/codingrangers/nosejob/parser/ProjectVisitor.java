package com.codingrangers.nosejob.parser;

import com.codingrangers.nosejob.models.ProjectData;
import com.codingrangers.nosejob.models.ClassData;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.body.*;

/**
 * parser for project level data enters project at CompilationUnit and exits at
 * ClassOrInterfaceDeclaration
 *
 * @author peter
 *
 */
public class ProjectVisitor extends VoidVisitorAdapter<ProjectData> {

	@Override
	public void visit(ClassOrInterfaceDeclaration classOrInterface, ProjectData projectData) {
		if (classOrInterface.isInterface() == false) {
			ClassVisitor visitor = new ClassVisitor();
			ClassData classData = null;
			visitor.visit(classOrInterface, classData);
		}
	}

}
