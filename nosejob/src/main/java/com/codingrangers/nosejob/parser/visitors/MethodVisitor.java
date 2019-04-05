package com.codingrangers.nosejob.parser.visitors;

import com.codingrangers.nosejob.models.MethodData;
import com.codingrangers.nosejob.models.VariableData;
import com.codingrangers.nosejob.parser.ParsedMethod;
import com.codingrangers.nosejob.parser.ParsedVariable;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.*;

/**
 * parser for method level data enters project at MethodDecloration and exits at
 * VariableDeclarationExpr
 *
 * @author peter
 *
 */
public class MethodVisitor extends VoidVisitorAdapter<ParsedMethod> {

	VoidVisitorAdapter<ParsedVariable> varibleVisitor;
	
	public MethodVisitor(VoidVisitorAdapter<ParsedVariable> variableVistor) {
		this.varibleVisitor = variableVistor;
	}
	
	public void visit(VariableDeclarator varaible, ParsedMethod methodData) {
		ParsedVariable variableData = methodData.createVariable(varaible.getNameAsString());
		varibleVisitor.visit(varaible, variableData);
	}

	/**
	 * entry point to MethodVisitor
	 */
	public void visit(MethodDeclaration method, ParsedMethod methodData) {
		System.out.println("method length: " + method.getBegin().get().line +"-"+method.getEnd().get().line);
		super.visit(method,methodData);
	}
	
}
