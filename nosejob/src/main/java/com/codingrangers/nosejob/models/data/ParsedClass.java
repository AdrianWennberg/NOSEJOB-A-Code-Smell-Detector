package com.codingrangers.nosejob.models.data;

import java.util.*;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

/**
 * ParsedClass
 * TODO: Need to unit test this
 */
public class ParsedClass implements ClassData {

    ClassOrInterfaceDeclaration declaration;
    String path;
    Map<String, MethodData> classMethods;
    Map<String, VariableData> classVariables;


    /* Uses the ClassOrInterfaceDeclaration from the Java Parser to get most of the class data.
     * I don't know if this will work, but I'm trying it for now. Not good for Unit Testing so it will
     * probably have to change. 
     * TODO: make this not depend on JavaParser.
     */ 
    public ParsedClass(ClassOrInterfaceDeclaration classDeclaration, String filePath) {
        declaration = classDeclaration;
        path = filePath;
        classMethods = new HashMap<>();
        classVariables = new HashMap<>();
    }

    public void addMethod(MethodData newMethod){
        classMethods.put(newMethod.getName(), newMethod);
    }

    public void addVariable(VariableData newVariable){
        classVariables.put(newVariable.getName(), newVariable);
    }

    @Override
    public String getName() {
        return declaration.getName().asString();
    }

    @Override
    public String getFullyQualifiedName() {
        // TODO
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
    public List<String> getMethodNames() {
        return new ArrayList<String>(classMethods.keySet());
    }

    @Override
    public MethodData getMethod(String name) {
        return classMethods.get(name);
    }

    @Override
    public List<String> getFieldsNames() {
        return new ArrayList<String>(classVariables.keySet());
    }

    @Override
    public VariableData getField(String name) {
        return classVariables.get(name);
    }
}