package com.codingrangers.nosejob.parser.visitors;

import java.util.EnumSet;

import com.codingrangers.nosejob.models.VariableData;
import com.codingrangers.nosejob.parser.ParsedVariable;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.Name;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.VariableDeclarationExpr;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.*;

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
		
		if(varaible.getParentNode().get() instanceof FieldDeclaration) {
			FieldDeclaration parent = (FieldDeclaration)varaible.getParentNode().get();
			if(parent.getModifiers().contains(Modifier.PUBLIC))
				System.out.println("is public");
			else if(parent.getModifiers().contains(Modifier.PRIVATE))
				System.out.println("is private");
			else if(parent.getModifiers().contains(Modifier.PROTECTED))
				System.out.println("is protected");
			else 
				System.out.println("is Default");
		}
		
		Type type = varaible.getType();
		System.out.println(type.asString() +" is prim: "+ type.isPrimitiveType());
		System.out.println(varaible.getBegin().get().line);
	}
	
}
