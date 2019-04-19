package com.codingrangers.nosejob.parser.visitors;

import com.codingrangers.nosejob.parser.data.ParsedMethod;
import com.codingrangers.nosejob.parser.data.ParsedVariable;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

/**
 * parser for method level data enters project at MethodDecloration and exits at
 * VariableDeclarationExpr
 *
 * @author peter
 *
 */
public class MethodVisitor extends VoidVisitorAdapter<ParsedMethod> {

	private VoidVisitorAdapter<ParsedVariable> variableVisitor;
	
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
