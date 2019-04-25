package com.codingrangers.nosejob.parser.visitors;

import com.codingrangers.nosejob.parser.data.ParsedClass;
import com.codingrangers.nosejob.parser.data.ParsedMethod;
import com.codingrangers.nosejob.parser.data.ParsedVariable;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.resolution.UnsolvedSymbolException;
import com.github.javaparser.resolution.declarations.ResolvedFieldDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedValueDeclaration;

/**
 * parser for class level data enters project from ClassOrInterfaceDeclaration
 * and exits from FieldDeclaration and MethodDeclaration
 *
 * @author peter
 *
 */
public class ClassVisitor extends VoidVisitorAdapter<ParsedClass> {

	private VoidVisitorAdapter<ParsedMethod> methodVisitor;
	private VoidVisitorAdapter<ParsedVariable> fieldVisitor;

	public ClassVisitor(VoidVisitorAdapter<ParsedMethod> methodVisitor,
						VoidVisitorAdapter<ParsedVariable> fieldVisitor) {
		this.methodVisitor = methodVisitor;
		this.fieldVisitor = fieldVisitor;
	}

	@Override
	public void visit(FieldDeclaration field, ParsedClass classData) {
		for (VariableDeclarator variable : field.getVariables()) {
			ParsedVariable variableData = classData.createField(variable.getNameAsString());
			fieldVisitor.visit(variable, variableData);
		}
		super.visit(field, classData);
	}

	@Override
	public void visit(MethodDeclaration method, ParsedClass classData) {
		ParsedMethod methodData = classData.createMethod(method.getSignature().asString());
		methodVisitor.visit(method, methodData);
	}
	
	public void visit(MethodCallExpr methodCall, ParsedClass classData ) {
		ResolvedMethodDeclaration resolvedMethod = methodCall.resolve();

		String fullQualifiedName = resolvedMethod.getQualifiedName();
		String classQualifedName = fullQualifiedName.substring(0, fullQualifiedName.lastIndexOf('.'));		
		
		classData.addReferenceToMethod(classQualifedName, resolvedMethod.getSignature());
	}
	
	public void visit(FieldAccessExpr fieldCall, ParsedClass classData) {
		ResolvedFieldDeclaration resolvedField = (ResolvedFieldDeclaration) fieldCall.resolve();
				
		classData.addReferenceToField(resolvedField.declaringType().getQualifiedName(), resolvedField.getName());
	}
	
	public void visit(NameExpr nameCall, ParsedClass classData) {
		try {
			ResolvedValueDeclaration resolvedName = nameCall.resolve();
			if(resolvedName.isField()) {
				ResolvedFieldDeclaration resolvedField = resolvedName.asField();
				classData.addReferenceToField(resolvedField.declaringType().getQualifiedName(), resolvedField.getName());
			}

		} catch (UnsolvedSymbolException e)
		{
			System.err.println("cannot resolve symbol: " + e.getName());
		}
	}

	@Override
	public void visit(ClassOrInterfaceDeclaration classOrInterface, ParsedClass classData) {
		if (classOrInterface.isInterface() == false) {
			classData.setStartLine(classOrInterface.getBegin().get().line);
			classData.setEndLine(classOrInterface.getEnd().get().line);
			super.visit(classOrInterface, classData);
		}
	}

	/**
	 * Class Visitor entry point
	 */
	@Override
	public void visit(CompilationUnit compilationUnit, ParsedClass classData) {
		super.visit(compilationUnit, classData);
	}

}
