package com.codingrangers.nosejob.parser.visitors;

import com.codingrangers.nosejob.parser.data.ParsedClass;
import com.codingrangers.nosejob.parser.data.ParsedMethod;
import com.codingrangers.nosejob.parser.data.ParsedVariable;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ConstructorDeclaration;
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

    @Override
    public void visit(ConstructorDeclaration constructor, ParsedClass classData) {
        ParsedMethod methodData = classData.createMethod(constructor.getSignature().asString());
        methodData.setReturnType(classData.getName(), false);
        methodVisitor.visit(constructor, methodData);
    }
	
	public void visit(MethodCallExpr methodCall, ParsedClass classData ) {
        try {
            ResolvedMethodDeclaration resolvedMethod = methodCall.resolve();

            String fullQualifiedName = resolvedMethod.getQualifiedName();
            String classQualifedName = fullQualifiedName.substring(0, fullQualifiedName.lastIndexOf('.'));

            classData.addReferenceToMethod(classQualifedName, resolvedMethod.getSignature());
        } catch (UnsolvedSymbolException e) {
            System.err.println("unsolved symbol found: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Got exception while resolving: " + e.getMessage());
        }
	}
	
	public void visit(FieldAccessExpr fieldCall, ParsedClass classData) {

        try {
            ResolvedValueDeclaration resolvedFieldCall = fieldCall.resolve();
            if (resolvedFieldCall.isField()) {
                ResolvedFieldDeclaration resolvedField = (ResolvedFieldDeclaration) resolvedFieldCall;

                classData.addReferenceToField(resolvedField.declaringType().getQualifiedName(), resolvedField.getName());
            }
        } catch (UnsolvedSymbolException e) {
            System.err.println("cannot resolve symbol: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Got exception while resolving: " + e.getMessage());
        }
	}
	
	public void visit(NameExpr nameCall, ParsedClass classData) {
		try {
			ResolvedValueDeclaration resolvedName = nameCall.resolve();
			if(resolvedName.isField()) {
				ResolvedFieldDeclaration resolvedField = resolvedName.asField();
				classData.addReferenceToField(resolvedField.declaringType().getQualifiedName(), resolvedField.getName());
			}

        } catch (UnsolvedSymbolException e) {
            System.err.println("cannot resolve symbol: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Got exception while resolving: " + e.getMessage());
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