/* NOSEJOB by The Coding Rangers
 * William Akinsanya
 * Alessandro Baccin
 * Peter Major
 * Adrian Wennberg
 * For the UCD module:
 * 	 Software Engineering Project 3
 */
 package com.codingrangers.nosejob.parser.data;

import com.codingrangers.nosejob.models.*;
import com.google.common.collect.Iterables;

import java.util.*;

public class ParsedClass extends ParsedCodeUnit implements ClassData, Cloneable {

    private final Map<String, MethodData> classMethods;
    private final Map<String, VariableData> classVariables;
    private ParsedMethod methodPrototype;
    private ParsedVariable fieldPrototype;
    private ReferenceStorage referenceStorage;
    private Set<String> superclasses;

    ParsedClass(String classNamePrefix, String className, String filePath) {
        super(classNamePrefix, className, filePath);
        classMethods = new HashMap<>();
        classVariables = new HashMap<>();
        methodPrototype = new ParsedMethod(getFullyQualifiedName(), "", filePath, this);
        fieldPrototype = new ParsedVariable(getFullyQualifiedName(), "", filePath);
        superclasses = new HashSet<>();
    }


    void setMethodPrototype(ParsedMethod newPrototype) {
        methodPrototype = newPrototype;
    }

    public ParsedMethod createMethod(String name) {
        ParsedMethod method = methodPrototype.clone();
        method.setName(name);
        method.setNamePrefix(getFullyQualifiedName());
        method.setFilePath(getFilePath());
        method.setReferenceStorage(referenceStorage);
        classMethods.put(method.getName(), method);
        return method;
    }

    @Override
    public int countMethods() {
        return classMethods.size();
    }

    @Override
    public List<String> getMethodSignatures() {
        return new ArrayList<>(classMethods.keySet());
    }

    @Override
    public MethodData getMethod(String name) {
        return classMethods.get(name);
    }


    void setFieldPrototype(ParsedVariable newPrototype) {
        fieldPrototype = newPrototype;
    }

    public ParsedVariable createField(String name) {
        ParsedVariable variable = fieldPrototype.clone();
        variable.setName(name);
        variable.setNamePrefix(getFullyQualifiedName());
        variable.setFilePath(getFilePath());
        classVariables.put(variable.getName(), variable);
        return variable;
    }

    @Override
    public int countFields() {
        return getFieldsNames().size();
    }

    @Override
    public int countStaticFields() {
        int count = 0;
        for (String fieldName : getFieldsNames()) {
            VariableData field = getField(fieldName);
            if (field.isStatic()) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int countPublicFields() {
        int count = 0;
        for (String fieldName : getFieldsNames()) {
            if (getField(fieldName).getAccessSpecifier() == AccessSpecifier.PUBLIC) {
                count++;
            }
        }
        return count;
    }

    @Override
    public int countStaticPublicFields() {
        int count = 0;
        for (String fieldName : getFieldsNames()) {
            VariableData field = getField(fieldName);
            if (field.getAccessSpecifier() == AccessSpecifier.PUBLIC && field.isStatic()) {
                count++;
            }
        }
        return count;
    }

    @Override
    public List<String> getFieldsNames() {
        return new ArrayList<>(classVariables.keySet());
    }

    @Override
    public VariableData getField(String name) {
        return classVariables.get(name);
    }

    @Override
    public int countPrimitiveFields() {
        return DataStructureHelpers.countPrimitives(classVariables.values());
    }

    @Override
    public boolean isSubClassOf(String classFullyQualifiedName) {
        return superclasses.contains(classFullyQualifiedName);
    }


    void setReferenceStorage(ReferenceStorage storage) {
        referenceStorage = storage;
    }

    @Override
    public int countInternalMethodCalls() {
        return countMethodCallsTo(getFullyQualifiedName());
    }

    @Override
    public Iterable<MethodReference> getInternalMethodCalls() {
        return getMethodCallsTo(getFullyQualifiedName());
    }

    @Override
    public int countMethodCallsTo(String fullyQualifiedClassName) {
        return Iterables.size(getMethodCallsTo(fullyQualifiedClassName));
    }

    @Override
    public Iterable<MethodReference> getMethodCallsTo(String fullyQualifiedClassName) {
        return referenceStorage.getMethodReference(getFullyQualifiedName(), fullyQualifiedClassName);
    }

    @Override
    public int countFieldReferencesTo(String fullyQualifiedClassName) {
        return Iterables.size(getFieldReferencesTo(fullyQualifiedClassName));
    }

    @Override
    public Iterable<FieldReference> getFieldReferencesTo(String fullyQualifiedClassName) {
        return referenceStorage.getFieldReference(getFullyQualifiedName(), fullyQualifiedClassName);
    }

    public ParsedMethodReference addReferenceToMethod(String fullyQualifiedClassName, String methodSignature) {
        boolean internal = getFullyQualifiedName().equals(fullyQualifiedClassName) ||
                isSubClassOf(fullyQualifiedClassName);

        ParsedMethodReference methodRef = new ParsedMethodReference(getFilePath(), getFullyQualifiedName(),
                fullyQualifiedClassName, methodSignature, internal);
        referenceStorage.add(methodRef);
        return methodRef;
    }

    public ParsedFieldReference addReferenceToField(String fullyQualifiedClassName, String fieldName) {
        boolean internal = getFullyQualifiedName().equals(fullyQualifiedClassName) ||
                isSubClassOf(fullyQualifiedClassName);

        ParsedFieldReference fieldRef = new ParsedFieldReference(getFilePath(), getFullyQualifiedName(),
                fullyQualifiedClassName, fieldName, internal);
        referenceStorage.add(fieldRef);
        return fieldRef;
    }

    @Override
    protected ParsedClass clone() {

        ParsedClass parsedClass = new ParsedClass(getNamePrefix(), getName(), getFilePath());
        parsedClass.superclasses = new HashSet<>();
        return parsedClass;
    }

    public void addSuperclass(String qualifiedName) {
        superclasses.add(qualifiedName);
    }
}
