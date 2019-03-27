package com.codingrangers.nosejob.models.parsing;

import com.codingrangers.nosejob.models.data.parsing.*;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.*;

/**
 * parser for method level data
 * enters project at MethodDecloration and exits at VariableDeclarationExpr
 * @author peter
 *
 */
public class MethodVisitor extends VoidVisitorAdapter<ParsedMethod>{

	public void visit(VariableDeclarator variable, ParsedMethod methodData) {
		VariableVisitor visitor = new VariableVisitor();
		ParsedVariable variableData = new ParsedVariable(methodData, 
				variable.getNameAsString(), 
				variable.getTypeAsString());
		visitor.visit(variable, variableData);
	}
	
}
