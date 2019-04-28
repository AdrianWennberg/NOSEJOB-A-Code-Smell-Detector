package com.codingrangers.nosejob.parser.visitors;

import com.codingrangers.nosejob.parser.data.ParsedClass;
import com.codingrangers.nosejob.parser.data.ParsedMethod;
import com.codingrangers.nosejob.parser.data.ParsedVariable;
import com.github.javaparser.ast.body.*;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.type.Type;
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
	private VoidVisitorAdapter<ParsedVariable> variableVisitor;

	public ClassVisitor(VoidVisitorAdapter<ParsedMethod> methodVisitor,
						VoidVisitorAdapter<ParsedVariable> variableVisitor) {
		this.methodVisitor = methodVisitor;
		this.variableVisitor = variableVisitor;
	}

	@Override
	public void visit(FieldDeclaration field, ParsedClass classData) {
		for (VariableDeclarator variable : field.getVariables()) {

			ParsedVariable variableData = classData.createField(variable.getNameAsString());
			variableVisitor.visit(variable, variableData);
		}
		super.visit(field, classData);
	}

	@Override
	public void visit(MethodDeclaration method, ParsedClass classData) {
		ParsedMethod methodData = classData.createMethod(method.getSignature().asString());

		Type returnType = method.getType();
		methodData.setReturnType(returnType.asString(), returnType.isPrimitiveType());

		for (Parameter p : method.getParameters()) {
			ParsedVariable parameterData = methodData.createParameter(p.getNameAsString());
			variableVisitor.visit(p, parameterData);
		}

		methodData.setStartLine(method.getBegin().get().line);
		methodData.setEndLine(method.getEnd().get().line);

		methodVisitor.visit(method, methodData);
	}

    @Override
    public void visit(ConstructorDeclaration constructor, ParsedClass classData) {
        ParsedMethod methodData = classData.createMethod(constructor.getSignature().asString());
        methodData.setReturnType(classData.getName(), false);

		methodData.setStartLine(constructor.getBegin().get().line);
		methodData.setEndLine(constructor.getEnd().get().line);

		for (Parameter p : constructor.getParameters()) {
			ParsedVariable parameterData = methodData.createParameter(p.getNameAsString());
			variableVisitor.visit(p, parameterData);
		}

        methodVisitor.visit(constructor, methodData);
    }
	
	public void visit(MethodCallExpr methodCall, ParsedClass classData ) {
        try {
            ResolvedMethodDeclaration resolvedMethod = methodCall.resolve();

            String fullQualifiedName = resolvedMethod.getQualifiedName();
			String classQualifiedName = fullQualifiedName.substring(0, fullQualifiedName.lastIndexOf('.'));

			classData.addReferenceToMethod(classQualifiedName, resolvedMethod.getSignature());
        } catch (UnsolvedSymbolException e) {
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
        } catch (Exception e) {
            System.err.println("Got exception while resolving: " + e.getMessage());
		}
	}

	@Override
	public void visit(ClassOrInterfaceDeclaration classOrInterface, ParsedClass parsedClass) {
		System.out.printf("Visiting: %s as: %s%n", classOrInterface.getNameAsString(), parsedClass.getName());
		if (classOrInterface.getNameAsString().equals(parsedClass.getName())) {
			System.out.println("Keeps visiting");
			super.visit(classOrInterface, parsedClass);
		}
	}
}