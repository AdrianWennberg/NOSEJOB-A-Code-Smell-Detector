package com.codingrangers.nosejob.parser;

import com.codingrangers.nosejob.models.IProjectData;
import com.codingrangers.nosejob.models.IClassData;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.body.*;

/**
 * parser for project level data enters project at CompilationUnit and exits at
 * ClassOrInterfaceDeclaration
 *
 * @author peter
 *
 */
public class ProjectVisitor extends VoidVisitorAdapter<IProjectData> {

	@Override
	public void visit(ClassOrInterfaceDeclaration classOrInterface, IProjectData IProjectData) {
		if (classOrInterface.isInterface() == false) {
			ClassVisitor visitor = new ClassVisitor();
			IClassData iClassData = null;
			visitor.visit(classOrInterface, iClassData);
		}
	}

}
