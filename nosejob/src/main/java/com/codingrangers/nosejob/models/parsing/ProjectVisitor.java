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
			ClassVisitor visitor = new ClassVisitor();
			ParsedClass parsedClass = new ParsedClass(
					"",
					classOrInterface.getNameAsString(),
					"PathGoesHere");

			visitor.visit(classOrInterface, parsedClass);
		}
	}
	
}
