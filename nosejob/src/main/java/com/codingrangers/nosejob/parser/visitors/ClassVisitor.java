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

	public void visit(FieldDeclaration field, ParsedClass classData) {
		for(VariableDeclarator varailbe : field.getVariables()) {
			VariableVisitor visitor = new VariableVisitor();
			ParsedVariable variableData = new ParsedVariable(classData, varailbe.getNameAsString());
			visitor.visit(varailbe, variableData);
		}
	}

	public void visit(MethodDeclaration method, ParsedClass classData) {
		MethodVisitor visitor = new MethodVisitor();
		ParsedMethod methodData = new ParsedMethod(classData, method.getNameAsString());
		visitor.visit(method, methodData);
	}

	public void visit(CompilationUnit cu, ParsedClass classData) {
		super.visit(cu, classData);
	}

}
