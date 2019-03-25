package com.codingrangers.nosejob.models.data.parsed;

import java.util.List;

import com.codingrangers.nosejob.models.data.MethodData;
import com.codingrangers.nosejob.models.data.VariableData;
import com.github.javaparser.ast.body.MethodDeclaration;

/**
 * ParsedMethod
 */
public class ParsedMethod implements MethodData {
    
    MethodDeclaration declaration;
    String path;

    ParsedMethod(MethodDeclaration methodDeclaration, String filePath){
        declaration = methodDeclaration;
        path = filePath;
    }

    @Override
    public String getName() {
        return declaration.getNameAsString();
    }

    @Override
    public String getFullyQualifiedName() {
        return null;
    }

    @Override
    public String getFilePath() {
        return path;
    }

    @Override
    public int getStartLineNumber() {
        return declaration.getBegin().get().line;
    }

    @Override
    public int getEndLineNumber() {
        return declaration.getEnd().get().line;
    }

    @Override
    public int getLineCount() {
        return getEndLineNumber() - getStartLineNumber();
    }

    @Override
    public String getClassName() {
        return declaration.asClassOrInterfaceDeclaration().getNameAsString();
    }

    @Override
    public String getReturnType() {
        return declaration.getTypeAsString();
    }

    @Override
    public boolean hasPrimitiveReturnType() {
        return declaration.getType().isPrimitiveType();
    }

    @Override
    public List<VariableData> getParameters() {
        return null;
    }

    @Override
    public List<VariableData> getLocalVariables() {
        return null;
    }

    @Override
    public int getPrimitiveParameterCount() {
        return 0;
    }

    @Override
    public int getPrimitiveLocalCount() {
        return 0;
    }
}