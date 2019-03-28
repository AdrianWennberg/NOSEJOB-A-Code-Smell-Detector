package com.codingrangers.nosejob.models.parsing;

import com.codingrangers.nosejob.models.data.parsing.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.body.*;

/**
 * parser for project level data
 * enters project at CompilationUnit and exits at ClassOrInterfaceDeclaration
 * @author peter
 *
 */
public class ProjectVisitor extends VoidVisitorAdapter<ParsedProject>{

	@Override
	public void visit(ClassOrInterfaceDeclaration classOrInterface, ParsedProject parsedProject) {
		if(classOrInterface.isInterface() == false) {
			ParsedClass parsedClass = new ParsedClass(
					"",
					classOrInterface.getNameAsString(),
					"PathGoesHere");

			ClassVisitor visitor = new ClassVisitor();
			visitor.visit(classOrInterface, parsedClass);
			parsedProject.addClass(parsedClass);
		}
	}
	
}
