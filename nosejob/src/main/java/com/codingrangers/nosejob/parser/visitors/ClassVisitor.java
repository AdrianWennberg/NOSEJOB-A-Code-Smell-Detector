package com.codingrangers.nosejob.parser.visitors;

import com.codingrangers.nosejob.parser.data.ParsedClass;
import com.codingrangers.nosejob.parser.data.ParsedMethod;
import com.codingrangers.nosejob.parser.data.ParsedVariable;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

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
	}

	@Override
	public void visit(MethodDeclaration method, ParsedClass classData) {
		ParsedMethod methodData = classData.createMethod(method.getSignature().asString());
		methodVisitor.visit(method, methodData);
	}

	@Override
	public void visit(ClassOrInterfaceDeclaration classOrInterface, ParsedClass classData) {
		if (classOrInterface.isInterface() == false) {
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
