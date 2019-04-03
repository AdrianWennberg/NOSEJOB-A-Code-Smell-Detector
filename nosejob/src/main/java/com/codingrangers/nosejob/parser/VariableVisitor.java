package com.codingrangers.nosejob.parser;

import com.codingrangers.nosejob.models.IVariableData;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.body.*;

/**
 * parsing for Variable level data enters project at VariableDeclarationExpr
 *
 * @author peter
 *
 */
public class VariableVisitor extends VoidVisitorAdapter<IVariableData> {

	public void visit(VariableDeclarator field, IVariableData variableData) {
		System.out.println(field.getNameAsString());
	}

}
