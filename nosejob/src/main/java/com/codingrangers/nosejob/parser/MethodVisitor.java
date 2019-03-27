package com.codingrangers.nosejob.parser;

import com.codingrangers.nosejob.models.MethodData;
import com.codingrangers.nosejob.models.VariableData;
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
public class MethodVisitor extends VoidVisitorAdapter<MethodData> {

	public void visit(VariableDeclarationExpr varaible, MethodData methodData) {
		VariableVisitor visitor = new VariableVisitor();
		VariableData variableData = null;
		visitor.visit(varaible, variableData);
	}

}
