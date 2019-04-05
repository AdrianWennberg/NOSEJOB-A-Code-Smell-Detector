package com.codingrangers.nosejob.parser.visitors;

import com.codingrangers.nosejob.models.ClassData;
import com.codingrangers.nosejob.models.VariableData;
import com.codingrangers.nosejob.parser.ParsedClass;
import com.codingrangers.nosejob.parser.ParsedMethod;
import com.codingrangers.nosejob.parser.ParsedVariable;
import com.codingrangers.nosejob.models.MethodData;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.*;

/**
 * parser for class level data enters project from ClassOrInterfaceDeclaration
 * and exits from FieldDeclaration and MethodDeclaration
 *
 * @author peter
 *
 */
public class ClassVisitor extends VoidVisitorAdapter<ParsedClass> {

	VoidVisitorAdapter<ParsedMethod> methodVisitor;
	VoidVisitorAdapter<ParsedVariable> feildVisitor;
	
	public ClassVisitor(VoidVisitorAdapter<ParsedMethod> methodVisitor, VoidVisitorAdapter<ParsedVariable> feildVisitor) {
		this.methodVisitor = methodVisitor;
		this.feildVisitor = feildVisitor;		
	}
	
	public void visit(FieldDeclaration field, ParsedClass classData) {
		for(VariableDeclarator varailbe : field.getVariables()) {
			ParsedVariable variableData = classData.createField(varailbe.getNameAsString());
			feildVisitor.visit(varailbe, variableData);
		}
	}

	public void visit(MethodDeclaration method, ParsedClass classData) {
		ParsedMethod methodData = classData.createMethod(method.getNameAsString());
		methodVisitor.visit(method, methodData);
	}
	
	public void visit(ClassOrInterfaceDeclaration classOrInterface, ParsedClass classData) {
		System.out.println("class length: " + classOrInterface.getBegin().get().line + "-" 
								+ classOrInterface.getEnd().get().line);
		super.visit(classOrInterface, classData);
	}

	public void visit(CompilationUnit cu, ParsedClass classData) {
		super.visit(cu, classData);
	}

}
