package com.codingrangers.nosejob.parser;

import com.codingrangers.nosejob.models.IMethodData;
import com.codingrangers.nosejob.models.IVariableData;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.expr.*;

/**
 * parser for method level data enters project at MethodDecloration and exits at
 * VariableDeclarationExpr
 *
 * @author peter
 *
 */
public class MethodVisitor extends VoidVisitorAdapter<IMethodData> {

	public void visit(VariableDeclarationExpr varaible, IMethodData methodData) {
		VariableVisitor visitor = new VariableVisitor();
		IVariableData variableData = null;
		visitor.visit(varaible, variableData);
	}

}
