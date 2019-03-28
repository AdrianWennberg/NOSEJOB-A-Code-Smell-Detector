package com.codingrangers.nosejob.models.parsing;

import com.codingrangers.nosejob.models.data.parsing.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;

/**
 * parser for class level data
 * enters project from ClassOrInterfaceDeclaration 
 * and exits from FieldDeclaration and MethodDeclaration
 * @author peter
 *
 */
public class ClassVisitor extends VoidVisitorAdapter<ParsedClass>{

	public void visit(VariableDeclarator variable, ParsedClass parsedClass) {
		ParsedVariable parsedVariable = new ParsedVariable(parsedClass,
															variable.getNameAsString());
				
		VariableVisitor visitor = new VariableVisitor();
		visitor.visit(variable,parsedVariable);
		
		parsedClass.addField(parsedVariable);
	}	
	
	public void visit(MethodDeclaration method, ParsedClass parsedClass) {
		ParsedMethod parsedMethod = new ParsedMethod(parsedClass, method.getNameAsString());
		
		MethodVisitor visitor = new MethodVisitor();
		visitor.visit(method,parsedMethod);
		
		
		parsedClass.addMethod(parsedMethod);
	}
	
}
