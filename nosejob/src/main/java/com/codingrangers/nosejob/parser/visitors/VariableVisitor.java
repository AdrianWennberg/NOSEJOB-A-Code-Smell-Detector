package com.codingrangers.nosejob.parser.visitors;

import com.codingrangers.nosejob.parser.ParsedVariable;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.body.*;

/**
 * parsing for Variable level data enters project at VariableDeclarationExpr
 *
 * @author peter
 *
 */
public class VariableVisitor extends VoidVisitorAdapter<ParsedVariable> {

	/**
	 * entry point for VariableVisitor
	 */
	@Override
	public void visit(VariableDeclarator variable, ParsedVariable variableData) {
		variableData.setStartLine(variable.getBegin().get().line);
		variableData.setEndLine(variable.getEnd().get().line);
		variableData.setVariableType(variable.getTypeAsString());
		if (variable.getType().isPrimitiveType())
			variableData.setIsPrimitive();

		// the commeted code bellow is temporarly documenting how to get the various
		// values
		// that will be needed in the near future

		/*
		 * System.out.println(variable.getNameAsString());
		 *
		 * if(variable.getParentNode().get() instanceof FieldDeclaration) {
		 * FieldDeclaration parent = (FieldDeclaration)variable.getParentNode().get();
		 * if(parent.getModifiers().contains(Modifier.PUBLIC))
		 * System.out.println("is public"); else
		 * if(parent.getModifiers().contains(Modifier.PRIVATE))
		 * System.out.println("is private"); else
		 * if(parent.getModifiers().contains(Modifier.PROTECTED))
		 * System.out.println("is protected"); else System.out.println("is Default"); }
		 *
		 * Type type = variable.getType(); System.out.println(type.asString()
		 * +" is prim: "+ type.isPrimitiveType());
		 * System.out.println(variable.getBegin().get().line);
		 */
	}

}
