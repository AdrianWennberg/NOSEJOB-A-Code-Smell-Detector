package com.codingrangers.nosejob.parser.visitors;

import com.codingrangers.nosejob.models.VariableData;
import com.codingrangers.nosejob.parser.ParsedVariable;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;

/**
 * parsing for Variable level data enters project at VariableDeclarationExpr
 *
 * @author peter
 *
 */
public class VariableVisitor extends VoidVisitorAdapter<ParsedVariable> {
	
	@Override
	public void visit(VariableDeclarator varaible, ParsedVariable variableData) {
		System.out.println(varaible.getNameAsString());
	}
	
}
