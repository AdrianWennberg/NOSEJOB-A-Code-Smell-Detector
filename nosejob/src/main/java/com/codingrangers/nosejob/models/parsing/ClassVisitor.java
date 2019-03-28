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
		VariableVisitor visitor = new VariableVisitor();
		
		ParsedVariable variableData = new ParsedVariable(parsedClass,
				variable.getNameAsString(), 
				variable.getTypeAsString());
				
		visitor.visit(variable,variableData);
	}	
	
	public void visit(MethodDeclaration method, ParsedClass parsedClass) {
		MethodVisitor visitor = new MethodVisitor();
		ParsedMethod parsedMethod = new ParsedMethod(parsedClass, method.getNameAsString());
		visitor.visit(method,parsedMethod);
	}
	
}
