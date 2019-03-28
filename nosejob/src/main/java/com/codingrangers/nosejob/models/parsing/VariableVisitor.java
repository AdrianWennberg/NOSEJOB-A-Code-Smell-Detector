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
		parsedVariable.setVariableType(variable.getTypeAsString());
		parsedVariable.setStartLine(variable.getRange().get().begin.line);
		parsedVariable.setEndLine(variable.getRange().get().end.line);
		
		if(variable.getType().isPrimitiveType())
			parsedVariable.setIsPrimitive();
	}
	
}
