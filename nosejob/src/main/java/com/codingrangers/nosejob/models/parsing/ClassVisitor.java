package com.codingrangers.nosejob.models.parsing;

import com.codingrangers.nosejob.models.data.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.body.*;

/**
 * parser for class level data
 * enters project from ClassOrInterfaceDeclaration 
 * and exits from FieldDeclaration and MethodDeclaration
 * @author peter
 *
 */
public class ClassVisitor extends VoidVisitorAdapter<ClassData>{

	public void visit(FieldDeclaration field, ClassData classData) {
		VariableVisitor visitor = new VariableVisitor();
		VariableData variableData = null;
		visitor.visit(field,variableData);
	}	
	
	public void visit(MethodDeclaration method, ClassData classData) {
		MethodVisitor visitor = new MethodVisitor();
		MethodData methodData = null;
		visitor.visit(method,methodData);
	}
	
}
