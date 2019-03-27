package com.codingrangers.nosejob.models.parsing;

import com.codingrangers.nosejob.models.data.parsing.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.body.*;


/**
 * parsing for Variable level data
 * enters project at VariableDeclarationExpr
 * @author peter
 *
 */
public class VariableVisitor extends VoidVisitorAdapter<ParsedVariable> {

	public void visit(VariableDeclarator variable, ParsedVariable parsedVariable) {
		System.out.println(variable.getNameAsString() + " " + variable.getTypeAsString());
	}
	
}
