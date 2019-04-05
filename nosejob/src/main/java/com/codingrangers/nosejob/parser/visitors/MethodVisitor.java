package com.codingrangers.nosejob.parser.visitors;

import com.codingrangers.nosejob.parser.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.body.*;

/**
 * parser for method level data enters project at MethodDecloration and exits at
 * VariableDeclarationExpr
 *
 * @author peter
 *
 */
public class MethodVisitor extends VoidVisitorAdapter<ParsedMethod> {

	VoidVisitorAdapter<ParsedVariable> variableVisitor;
	
	public MethodVisitor(VoidVisitorAdapter<ParsedVariable> variableVistor) {
		this.variableVisitor = variableVistor;
	}
	
	@Override
	public void visit(VariableDeclarator variable, ParsedMethod methodData) {
		ParsedVariable variableData = methodData.createVariable(variable.getNameAsString());
		variableVisitor.visit(variable, variableData);
	}

	/**
	 * entry point to MethodVisitor
	 */
	@Override
	public void visit(MethodDeclaration method, ParsedMethod methodData) {
		System.out.println("method length: " + method.getBegin().get().line +"-"+method.getEnd().get().line);
		super.visit(method,methodData);
	}
	
}
