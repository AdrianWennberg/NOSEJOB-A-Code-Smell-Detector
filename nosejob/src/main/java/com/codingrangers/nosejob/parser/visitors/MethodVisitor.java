package com.codingrangers.nosejob.parser.visitors;

import com.codingrangers.nosejob.parser.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;

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
		ParsedVariable returnTypeVar = methodData.createVariable(method.getSignature().asString());
		
		for(Parameter p : method.getParameters()) {
			ParsedVariable paramaterData = methodData.createParameter(p.getNameAsString());
			variableVisitor.visit(p, paramaterData);
		}
		
		super.visit(method,methodData);
	}
	
}
