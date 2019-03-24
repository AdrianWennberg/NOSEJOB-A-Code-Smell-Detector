package com.codingrangers.nosejob.models.parsing;

import com.codingrangers.nosejob.models.data.VariableData;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.body.*;


/**
 * parsing for Variable level data
 * enters project at VariableDeclarationExpr
 * @author peter
 *
 */
public class VariableVisitor extends VoidVisitorAdapter<VariableData> {

	public void visit(VariableDeclarator field, VariableData variableData) {
		System.out.println(field.getNameAsString());
	}
	
}
