package com.codingrangers.nosejob.parser;

import com.codingrangers.nosejob.models.IClassData;
import com.codingrangers.nosejob.models.IVariableData;
import com.codingrangers.nosejob.models.IMethodData;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.body.*;

/**
 * parser for class level data enters project from ClassOrInterfaceDeclaration
 * and exits from FieldDeclaration and MethodDeclaration
 *
 * @author peter
 *
 */
public class ClassVisitor extends VoidVisitorAdapter<IClassData> {

	public void visit(FieldDeclaration field, IClassData iClassData) {
		VariableVisitor visitor = new VariableVisitor();
		IVariableData variableData = null;
		visitor.visit(field, variableData);
	}

	public void visit(MethodDeclaration method, IClassData iClassData) {
		MethodVisitor visitor = new MethodVisitor();
		IMethodData methodData = null;
		visitor.visit(method, methodData);
	}

}
