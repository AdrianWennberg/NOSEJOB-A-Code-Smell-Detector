package com.codingrangers.nosejob.parser.visitors;

import com.codingrangers.nosejob.parser.data.ParsedMethod;
import com.codingrangers.nosejob.parser.data.ParsedVariable;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
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

                methodData.addReferenceToField(resolvedField.declaringType().getQualifiedName(), resolvedField.getName());
            }
        } catch (UnsolvedSymbolException e) {
            System.err.println("cannot resolve symbol: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Got exception while resolving: " + e.getMessage());
		}
	}

    public void visit(NameExpr nameCall, ParsedMethod methodData) {
        try {
            ResolvedValueDeclaration resolvedName = nameCall.resolve();

            if (resolvedName.isField()) {
                ResolvedFieldDeclaration resolvedField = resolvedName.asField();
                methodData.addReferenceToField(resolvedField.declaringType().getQualifiedName(), resolvedField.getName());
            }
        } catch (UnsolvedSymbolException e) {
            System.err.println("cannot resolve symbol: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Got exception while resolving: " + e.getMessage());
		}
	}
	
	public void visit(MethodCallExpr methodCall, ParsedMethod methodData ) {
		try {
			ResolvedMethodDeclaration resolvedMethod = methodCall.resolve();

			String fullQualifiedName = resolvedMethod.getQualifiedName();
			String classQualifedName = fullQualifiedName.substring(0, fullQualifiedName.lastIndexOf('.'));

			methodData.addReferenceToMethod(classQualifedName, resolvedMethod.getSignature());
        } catch (UnsolvedSymbolException e) {
            System.err.println("cannot resolve symbol: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Got exception while resolving: " + e.getMessage());
		}
		
	}
	
	/**
	 * entry point to MethodVisitor
	 */
	@Override
	public void visit(MethodDeclaration method, ParsedMethod methodData) {
		Type returnType = method.getType();
		methodData.setReturnType(returnType.asString(), returnType.isPrimitiveType());
		
		for(Parameter p : method.getParameters()) {
            ParsedVariable parameterData = methodData.createParameter(p.getNameAsString());
            variableVisitor.visit(p, parameterData);
		}
		
		super.visit(method,methodData);
	}

    @Override
    public void visit(ConstructorDeclaration constructor, ParsedMethod methodData) {
        for (Parameter p : constructor.getParameters()) {
            ParsedVariable parameterData = methodData.createParameter(p.getNameAsString());
            variableVisitor.visit(p, parameterData);
        }

        super.visit(constructor, methodData);
    }
	
}
