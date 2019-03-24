package com.codingrangers.nosejob.models.parsing;

import com.codingrangers.nosejob.models.data.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.body.*;

/**
 * parser for project level data
 * enters project at CompilationUnit and exits at ClassOrInterfaceDeclaration
 * @author peter
 *
 */
public class ProjectVisitor extends VoidVisitorAdapter<ProjectData>{

	@Override
	public void visit(ClassOrInterfaceDeclaration classOrInterface, ProjectData projectData) {
		if(classOrInterface.isInterface() == false) {
			ClassVisitor visitor = new ClassVisitor();
			ClassData classData = null;
			visitor.visit(classOrInterface, classData);
		}
	}
	
}
