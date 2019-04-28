package com.codingrangers.nosejob.parser.visitors;

import com.codingrangers.nosejob.parser.data.ParsedMethod;
import com.codingrangers.nosejob.parser.data.ParsedVariable;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.github.javaparser.resolution.declarations.ResolvedFieldDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedMethodDeclaration;
import com.github.javaparser.resolution.declarations.ResolvedValueDeclaration;

/**
 * parser for method level data enters project at MethodDecloration and exits at
 * VariableDeclarationExpr
 *
 * @author peter
 *
 */
public class MethodVisitor extends VoidVisitorAdapter<ParsedMethod> {

	private VoidVisitorAdapter<ParsedVariable> variableVisitor;

    public MethodVisitor(VoidVisitorAdapter<ParsedVariable> variableVistor) {
		this.variableVisitor = variableVistor;
	}

    @Override
	public void visit(VariableDeclarator variable, ParsedMethod methodData) {
		ParsedVariable variableData = methodData.createVariable(variable.getNameAsString());
		variableVisitor.visit(variable, variableData);
		super.visit(variable, methodData);
	}

    public void visit(FieldAccessExpr fieldCall, ParsedMethod methodData ) {
        try {
            ResolvedValueDeclaration valueDeclaration = fieldCall.resolve();

            if (valueDeclaration.isField()) {
                ResolvedFieldDeclaration resolvedField = (ResolvedFieldDeclaration) valueDeclaration;

                String otherClassName = resolvedField.declaringType().getQualifiedName();
                String fieldName = resolvedField.getName();

                methodData.addReferenceToField(otherClassName, fieldName);
            }
        } catch (Exception ignored) {
        }
	}

    public void visit(NameExpr nameCall, ParsedMethod methodData) {
        try {
            ResolvedValueDeclaration resolvedName = nameCall.resolve();

            if (resolvedName.isField()) {
                ResolvedFieldDeclaration resolvedField = resolvedName.asField();

                String className = resolvedField.declaringType().getQualifiedName();
                String fieldName = resolvedField.getName();
                methodData.addReferenceToField(className, fieldName);
            }
        } catch (Exception ignored) {
        }
	}

    public void visit(MethodCallExpr methodCall, ParsedMethod methodData ) {
		try {
			ResolvedMethodDeclaration resolvedMethod = methodCall.resolve();

			String fullQualifiedName = resolvedMethod.getQualifiedName();
            String classQualifiedName = fullQualifiedName.substring(0, fullQualifiedName.lastIndexOf('.'));

            methodData.addReferenceToMethod(classQualifiedName, resolvedMethod.getSignature());
        } catch (Exception ignored) {
        }

    }
}
