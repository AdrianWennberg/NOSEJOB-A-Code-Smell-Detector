package com.codingrangers.nosejob.parser.visitors;

import com.codingrangers.nosejob.parser.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.body.*;
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
	
	@Override
	public void visit(FieldDeclaration field, ParsedClass classData) {
		for(VariableDeclarator variable : field.getVariables()) {
			ParsedVariable variableData = classData.createField(variable.getNameAsString());
			feildVisitor.visit(variable, variableData);
		}
	}

	@Override
	public void visit(MethodDeclaration method, ParsedClass classData) {
		ParsedMethod methodData = classData.createMethod(method.getNameAsString());
		methodVisitor.visit(method, methodData);
	}
	
	@Override
	public void visit(ClassOrInterfaceDeclaration classOrInterface, ParsedClass classData) {
		if(classOrInterface.isInterface() == false) {
				System.out.println("class length: " + classOrInterface.getBegin().get().line + "-" 
										+ classOrInterface.getEnd().get().line);
				super.visit(classOrInterface, classData);
		}
	}
	
	/**
	 * Class Visitor entry point
	 */
	@Override
	public void visit(CompilationUnit compilationUnit, ParsedClass classData) {
		super.visit(compilationUnit, classData);
	}

}
