package com.codingrangers.nosejob.parser.data;

import com.codingrangers.nosejob.models.CodeData;
import com.codingrangers.nosejob.models.FieldReference;
import com.codingrangers.nosejob.models.MethodReference;
import com.codingrangers.nosejob.models.VariableData;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ParsedMethodTest {
    @Test
    public void nameIsSetCorrectly() {
        String fullyQualifiedClassName = "name.prefix.ClassName";

        String methodSignature = "methodName(int)";
        String fullyQualifiedMethodName = "name.prefix.ClassName.methodName(int)";

        CodeData parsedMethod = new ParsedMethod(fullyQualifiedClassName, methodSignature, "");

        assertEquals(methodSignature, parsedMethod.getName());
        assertEquals(fullyQualifiedMethodName, parsedMethod.getFullyQualifiedName());

        fullyQualifiedClassName = "other.name.prefix.OtherClassName";

        methodSignature = "otherMethodName(int, float)";
        fullyQualifiedMethodName = "other.name.prefix.OtherClassName.otherMethodName(int, float)";

        parsedMethod = new ParsedMethod(fullyQualifiedClassName, methodSignature, "");

        assertEquals(methodSignature, parsedMethod.getName());
        assertEquals(fullyQualifiedMethodName, parsedMethod.getFullyQualifiedName());
    }

    @Test
    public void pathSetCorrectly() {
        String path = "C:/File/path.java";
        CodeData parsedMethod = new ParsedMethod("", "", path);

        assertEquals(path, parsedMethod.getFilePath());

        path = "C:/File/other/path.java";

        parsedMethod = new ParsedMethod("", "", path);

        assertEquals(path, parsedMethod.getFilePath());
    }

    @Test
    public void canSetLineNumbers() {
        int startLine = 5;
        int endLine = 100;
        int lineCount = 96;

        ParsedMethod parsedMethod = new ParsedMethod("", "", "");

        parsedMethod.setStartLine(startLine);
        parsedMethod.setEndLine(endLine);

        assertEquals(startLine, parsedMethod.getStartLineNumber());
        assertEquals(endLine, parsedMethod.getEndLineNumber());
        assertEquals(lineCount, parsedMethod.getLineCount());

        startLine = 10;
        endLine = 10;
        lineCount = 1;

        parsedMethod = new ParsedMethod("", "", "");
        parsedMethod.setStartLine(startLine);
        parsedMethod.setEndLine(endLine);

        assertEquals(startLine, parsedMethod.getStartLineNumber());
        assertEquals(endLine, parsedMethod.getEndLineNumber());
        assertEquals(lineCount, parsedMethod.getLineCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeStartLine() {

        ParsedMethod parsedMethod = new ParsedMethod("", "", "");
        parsedMethod.setStartLine(-5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeEndLine() {

        ParsedMethod parsedMethod = new ParsedMethod("", "", "");
        parsedMethod.setEndLine(-4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void startLineGreaterThanEndLine() {

        ParsedMethod parsedMethod = new ParsedMethod("", "", "");
        parsedMethod.setEndLine(5);
        parsedMethod.setStartLine(6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void endLineLessThanStartLine() {

        ParsedMethod parsedMethod = new ParsedMethod("", "", "");
        parsedMethod.setStartLine(10);
        parsedMethod.setEndLine(9);
    }

    @Test(expected = IllegalStateException.class)
    public void startLineAlreadySet() {

        ParsedMethod parsedMethod = new ParsedMethod("", "", "");
        parsedMethod.setStartLine(10);
        parsedMethod.setStartLine(10);
    }

    @Test(expected = IllegalStateException.class)
    public void endLineAlreadySet() {

        ParsedMethod parsedMethod = new ParsedMethod("", "", "");
        parsedMethod.setEndLine(12);
        parsedMethod.setEndLine(12);
    }

    @Test
    public void canGetClassName() {
        String className = "testClass";

        ParsedMethod parsedMethod = new ParsedMethod(className, "", "");

        assertEquals(className, parsedMethod.getClassName());
    }

    @Test
    public void canAddReturnType() {
        VariableData mockedReturnType = mock(VariableData.class);
        when(mockedReturnType.isPrimitive()).thenReturn(true);


        ParsedMethod parsedMethod = new ParsedMethod("", "", "");

        parsedMethod.setReturnType(mockedReturnType);

        assertEquals(mockedReturnType, parsedMethod.getReturnType());
        assertTrue(parsedMethod.hasPrimitiveReturnType());

        mockedReturnType = mock(VariableData.class);
        when(mockedReturnType.isPrimitive()).thenReturn(false);


        parsedMethod = new ParsedMethod("", "", "");

        parsedMethod.setReturnType(mockedReturnType);

        assertEquals(mockedReturnType, parsedMethod.getReturnType());
        assertFalse(parsedMethod.hasPrimitiveReturnType());
    }

    @Test
    public void canCreateDefaultParameter() {
        ParsedMethod parsedMethod = new ParsedMethod("", "", "");

        ParsedVariable parsedVariable = parsedMethod.createParameter("");
        parsedVariable.setIsPrimitive();

        List<VariableData> parameters = parsedMethod.getParameters();

        assertEquals(1, parameters.size());
        assertEquals(parsedVariable, parameters.get(0));
        assertEquals(1, parsedMethod.getPrimitiveParameterCount());
    }

    @Test
    public void canCreateMultipleDefaultParameters() {
        ParsedMethod parsedMethod = new ParsedMethod("", "", "");

        ParsedVariable firstParsedVariable = parsedMethod.createParameter("");
        ParsedVariable secondParsedVariable = parsedMethod.createParameter("");
        firstParsedVariable.setIsPrimitive();

        List<VariableData> parameters = parsedMethod.getParameters();

        assertEquals(2, parameters.size());
        assertThat(parameters, containsInAnyOrder(firstParsedVariable, secondParsedVariable));
        assertEquals(1, parsedMethod.getPrimitiveParameterCount());
    }

    @Test
    public void canCreateLocalVariable() {
        ParsedMethod parsedMethod = new ParsedMethod("", "", "");

        ParsedVariable parsedVariable = parsedMethod.createVariable("");
        parsedVariable.setIsPrimitive();

        List<VariableData> localVariables = parsedMethod.getLocalVariables();

        assertEquals(1, localVariables.size());
        assertEquals(parsedVariable, localVariables.get(0));
        assertEquals(1, parsedMethod.getPrimitiveLocalCount());
    }

    @Test
    public void canAddMultipleLocalVariables() {
        ParsedMethod parsedMethod = new ParsedMethod("", "", "");

        ParsedVariable firstParsedVariable = parsedMethod.createVariable("");
        firstParsedVariable.setIsPrimitive();
        ParsedVariable secondParsedVariable = parsedMethod.createVariable("");

        List<VariableData> localVariables = parsedMethod.getLocalVariables();

        assertEquals(2, localVariables.size());
        assertThat(localVariables, containsInAnyOrder(firstParsedVariable, secondParsedVariable));
        assertEquals(1, parsedMethod.getPrimitiveLocalCount());
    }


    @Test
    public void canAddMethodReferences() {
        String methodSignature = "foo()";
        String namePrefix = "name.prefix.ClassName";
        ParsedMethod parsedMethod = new ParsedMethod(namePrefix, methodSignature, "");

        ReferenceStorage mockedStorage = mock(ReferenceStorage.class);
        parsedMethod.setReferenceStorage(mockedStorage);

        String otherClassName = "name.other.OtherClassName";
        String calledMethodSignature = "bar()";

        MethodReference reference = parsedMethod.addReferenceToMethod(otherClassName, calledMethodSignature);

        assertEquals(namePrefix, reference.getReferencingClassName());
        assertEquals(otherClassName, reference.getReferredClassName());
        assertEquals(calledMethodSignature, reference.getReferredMethodSignature());

        verify(mockedStorage, times(1)).add(reference);
    }

    @Test
    public void canAddFieldReferences() {
        String methodSignature = "foo()";
        String namePrefix = "name.prefix.ClassName";
        ParsedMethod parsedMethod = new ParsedMethod(namePrefix, methodSignature, "");

        ReferenceStorage mockedStorage = mock(ReferenceStorage.class);
        parsedMethod.setReferenceStorage(mockedStorage);
        String otherClassName = "name.other.OtherClassName";
        String fieldName = "bar";

        FieldReference reference = parsedMethod.addReferenceToField(otherClassName, fieldName);

        assertEquals(namePrefix, reference.getReferencingClassName());
        assertEquals(otherClassName, reference.getReferredClassName());
        assertEquals(fieldName, reference.getReferredFieldName());

        verify(mockedStorage, times(1)).add(reference);
    }

    @Test
    public void canClone() {
        String methodSignature = "foo()";
        String namePrefix = "name.prefix.ClassName";
        String fullyQualifiedName = "name.prefix.ClassName.foo()";
        String path = "C:/File/path.java";
        ParsedMethod parsedMethod = new ParsedMethod(namePrefix, methodSignature, path);

        assertEquals(methodSignature, parsedMethod.getName());
        assertEquals(fullyQualifiedName, parsedMethod.getFullyQualifiedName());
        assertEquals(path, parsedMethod.getFilePath());
        assertEquals(namePrefix, parsedMethod.getClassName());

        CodeData clonedMethod = parsedMethod.clone();

        assertEquals(methodSignature, clonedMethod.getName());
        assertEquals(fullyQualifiedName, clonedMethod.getFullyQualifiedName());
        assertEquals(path, clonedMethod.getFilePath());
        assertEquals(namePrefix, parsedMethod.getClassName());
    }
}
