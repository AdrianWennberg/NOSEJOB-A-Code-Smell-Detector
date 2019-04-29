/* NOSEJOB by The Coding Rangers
 * William Akinsanya
 * Alessandro Baccin
 * Peter Major
 * Adrian Wennberg
 * For the UCD module:
 * 	 Software Engineering Project 3
 */
 package com.codingrangers.nosejob.parser.visitors;

import com.codingrangers.nosejob.models.AccessSpecifier;
import com.codingrangers.nosejob.parser.data.ParsedVariable;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

/**
 * parsing for Variable level data enters project at VariableDeclarationExpr
 *
 * @author peter
 *
 */
public class VariableVisitor extends VoidVisitorAdapter<ParsedVariable> {

	@Override
	public void visit(VariableDeclarator variable, ParsedVariable variableData) {
		variableData.setStartLine(variable.getBegin().get().line);
		variableData.setEndLine(variable.getEnd().get().line);
		
		variableData.setVariableType(variable.getTypeAsString());
		if(variable.getType().isPrimitiveType())
			variableData.setIsPrimitive();
		
		if(variable.getParentNode().get() instanceof FieldDeclaration) {
			FieldDeclaration parent = (FieldDeclaration)variable.getParentNode().get();
			if(parent.getModifiers().contains(Modifier.PUBLIC))
				variableData.setAccessSpecifier(AccessSpecifier.PUBLIC);
			else if(parent.getModifiers().contains(Modifier.PRIVATE))
				variableData.setAccessSpecifier(AccessSpecifier.PRIVATE);
			else if(parent.getModifiers().contains(Modifier.PROTECTED))
				variableData.setAccessSpecifier(AccessSpecifier.PROTECTED);
			else 
				variableData.setAccessSpecifier(AccessSpecifier.DEFAULT);

            if (parent.isStatic())
                variableData.setIsStatic();
		}
    }

    @Override
    public void visit(Parameter parameter, ParsedVariable variableData) {

        variableData.setStartLine(parameter.getBegin().get().line);
        variableData.setEndLine(parameter.getEnd().get().line);

        variableData.setVariableType(parameter.getTypeAsString());
        if (parameter.getType().isPrimitiveType())
            variableData.setIsPrimitive();
        variableData.setAccessSpecifier(AccessSpecifier.DEFAULT);
    }
}
