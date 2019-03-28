package com.codingrangers.nosejob.models.parsing;

import com.codingrangers.nosejob.models.data.parsing.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.type.Type;

/**
 * parser for method level data
 * enters project at MethodDecloration and exits at VariableDeclarationExpr
 * @author peter
 *
 */
public class MethodVisitor extends VoidVisitorAdapter<ParsedMethod>{

	public void visit(Parameter paramater, ParsedMethod parsedMethod) {
		ParsedVariable parsedVariable = new ParsedVariable(parsedMethod, 
				paramater.getNameAsString(), 
				paramater.getTypeAsString());
		
		VariableVisitor visitor = new VariableVisitor();
		visitor.visit(paramater, parsedVariable);
		
		parsedMethod.addParameter(parsedVariable);
	}
	
	public void visit(VariableDeclarator variableDeclarator, ParsedMethod parsedMethod) {
		ParsedVariable parsedVariable = new ParsedVariable(parsedMethod, 
				variableDeclarator.getNameAsString(), 
				variableDeclarator.getNameAsString());
		
		VariableVisitor visitor = new VariableVisitor();
		visitor.visit(variableDeclarator, parsedVariable);
		
		parsedMethod.addVariable(parsedVariable);
	}
	
}
