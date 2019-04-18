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

        CodeData parsedMethod = new ParsedMethod(fullyQualifiedClassName, methodSignature, "", "");

        assertEquals(methodSignature, parsedMethod.getName());
        assertEquals(fullyQualifiedMethodName, parsedMethod.getFullyQualifiedName());

        fullyQualifiedClassName = "other.name.prefix.OtherClassName";

        methodSignature = "otherMethodName(int, float)";
        fullyQualifiedMethodName = "other.name.prefix.OtherClassName.otherMethodName(int, float)";

        parsedMethod = new ParsedMethod(fullyQualifiedClassName, methodSignature, "", "");

        assertEquals(methodSignature, parsedMethod.getName());
        assertEquals(fullyQualifiedMethodName, parsedMethod.getFullyQualifiedName());
    }

    @Test
    public void pathSetCorrectly() {
        String path = "C:/File/path.java";
        CodeData parsedMethod = new ParsedMethod("", "", path, "");

        assertEquals(path, parsedMethod.getFilePath());

        path = "C:/File/other/path.java";

        parsedMethod = new ParsedMethod("", "", path, "");

        assertEquals(path, parsedMethod.getFilePath());
    }

    @Test
    public void canSetLineNumbers() {
        int startLine = 5;
        int endLine = 100;
        int lineCount = 96;

        ParsedMethod parsedMethod = new ParsedMethod("", "", "", "");

        parsedMethod.setStartLine(startLine);
        parsedMethod.setEndLine(endLine);

        assertEquals(startLine, parsedMethod.getStartLineNumber());
        assertEquals(endLine, parsedMethod.getEndLineNumber());
        assertEquals(lineCount, parsedMethod.getLineCount());

        startLine = 10;
        endLine = 10;
        lineCount = 1;

        parsedMethod = new ParsedMethod("", "", "", "");
        parsedMethod.setStartLine(startLine);
        parsedMethod.setEndLine(endLine);

        assertEquals(startLine, parsedMethod.getStartLineNumber());
        assertEquals(endLine, parsedMethod.getEndLineNumber());
        assertEquals(lineCount, parsedMethod.getLineCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeStartLine() {

        ParsedMethod parsedMethod = new ParsedMethod("", "", "", "");
        parsedMethod.setStartLine(-5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeEndLine() {

        ParsedMethod parsedMethod = new ParsedMethod("", "", "", "");
        parsedMethod.setEndLine(-4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void startLineGreaterThanEndLine() {

        ParsedMethod parsedMethod = new ParsedMethod("", "", "", "");
        parsedMethod.setEndLine(5);
        parsedMethod.setStartLine(6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void endLineLessThanStartLine() {

        ParsedMethod parsedMethod = new ParsedMethod("", "", "", "");
        parsedMethod.setStartLine(10);
        parsedMethod.setEndLine(9);
    }

    @Test(expected = IllegalStateException.class)
    public void startLineAlreadySet() {

        ParsedMethod parsedMethod = new ParsedMethod("", "", "", "");
        parsedMethod.setStartLine(10);
        parsedMethod.setStartLine(10);
    }

    @Test(expected = IllegalStateException.class)
    public void endLineAlreadySet() {

        ParsedMethod parsedMethod = new ParsedMethod("", "", "", "");
        parsedMethod.setEndLine(12);
        parsedMethod.setEndLine(12);
    }

    @Test
    public void canGetClassName() {
        String className = "testClass";

        ParsedMethod parsedMethod = new ParsedMethod("", "", "", className);

        assertEquals(className, parsedMethod.getClassName());
    }

    @Test
    public void canAddReturnType() {
        VariableData mockedReturnType = mock(VariableData.class);
        when(mockedReturnType.isPrimitive()).thenReturn(true);


        ParsedMethod parsedMethod = new ParsedMethod("", "", "", "");

        parsedMethod.setReturnType(mockedReturnType);

        assertEquals(mockedReturnType, parsedMethod.getReturnType());
        assertTrue(parsedMethod.hasPrimitiveReturnType());

        mockedReturnType = mock(VariableData.class);
        when(mockedReturnType.isPrimitive()).thenReturn(false);


        parsedMethod = new ParsedMethod("", "", "", "");

        parsedMethod.setReturnType(mockedReturnType);

        assertEquals(mockedReturnType, parsedMethod.getReturnType());
        assertFalse(parsedMethod.hasPrimitiveReturnType());
    }

    @Test
    public void canAddParameter() {
        VariableData mockedParameter = mock(VariableData.class);
        when(mockedParameter.isPrimitive()).thenReturn(true);


        ParsedMethod parsedMethod = new ParsedMethod("", "", "", "");

        parsedMethod.addParameter(mockedParameter);

        List<VariableData> parameters = parsedMethod.getParameters();

        assertEquals(1, parameters.size());
        assertEquals(mockedParameter, parameters.get(0));
        assertEquals(1, parsedMethod.getPrimitiveParameterCount());
    }

    @Test
    public void canAddMultipleParameters() {
        VariableData firstMockedParameter = mock(VariableData.class);
        when(firstMockedParameter.isPrimitive()).thenReturn(false);

        VariableData secondMockedParameter = mock(VariableData.class);
        when(secondMockedParameter.isPrimitive()).thenReturn(true);


        ParsedMethod parsedMethod = new ParsedMethod("", "", "", "");

        parsedMethod.addParameter(firstMockedParameter);
        parsedMethod.addParameter(secondMockedParameter);

        List<VariableData> parameters = parsedMethod.getParameters();

        assertEquals(2, parameters.size());
        assertThat(parameters, containsInAnyOrder(firstMockedParameter, secondMockedParameter));
        assertEquals(1, parsedMethod.getPrimitiveParameterCount());
    }

    @Test
    public void canAddLocalVariable() {
        VariableData mockedVariable = mock(VariableData.class);
        when(mockedVariable.isPrimitive()).thenReturn(true);


        ParsedMethod parsedMethod = new ParsedMethod("", "", "", "");

        parsedMethod.addVariable(mockedVariable);

        List<VariableData> localVariables = parsedMethod.getLocalVariables();

        assertEquals(1, localVariables.size());
        assertEquals(mockedVariable, localVariables.get(0));
        assertEquals(1, parsedMethod.getPrimitiveLocalCount());
    }

    @Test
    public void canAddMultipleLocalVariables() {
        VariableData firstMockedVariable = mock(VariableData.class);
        when(firstMockedVariable.isPrimitive()).thenReturn(false);

        VariableData secondMockedVariable = mock(VariableData.class);
        when(secondMockedVariable.isPrimitive()).thenReturn(true);


        ParsedMethod parsedMethod = new ParsedMethod("", "", "", "");

        parsedMethod.addVariable(firstMockedVariable);
        parsedMethod.addVariable(secondMockedVariable);

        List<VariableData> localVariables = parsedMethod.getLocalVariables();

        assertEquals(2, localVariables.size());
        assertThat(localVariables, containsInAnyOrder(firstMockedVariable, secondMockedVariable));
        assertEquals(1, parsedMethod.getPrimitiveLocalCount());
    }


    @Test
    public void canAddMethodReferences() {
        String methodSignature = "foo()";
        String className = "ClassName";
        String namePrefix = "name.prefix.ClassName";
        String fullyQualifiedName = "name.prefix.ClassName.foo()";
        ParsedMethod parsedMethod = new ParsedMethod(namePrefix, methodSignature, "", className);

        ReferenceStorage mockedStorage = mock(ReferenceStorage.class);
        parsedMethod.setReferenceStorage(mockedStorage);

        String otherClassName = "name.other.OtherClassName";
        String calledMethodSignature = "bar()";

        MethodReference reference = parsedMethod.addReferenceToMethod(otherClassName, calledMethodSignature);

        assertEquals(fullyQualifiedName, reference.getReferredClassName());
        assertEquals(otherClassName, reference.getReferencingClassName());
        assertEquals(calledMethodSignature, reference.getReferredMethodSignature());

        verify(mockedStorage, times(1)).add(reference);
    }

    @Test
    public void canAddFieldReferences() {
        String methodSignature = "foo()";
        String className = "ClassName";
        String namePrefix = "name.prefix.ClassName";
        String fullyQualifiedName = "name.prefix.ClassName.foo()";
        ParsedMethod parsedMethod = new ParsedMethod(namePrefix, methodSignature, "", className);

        ReferenceStorage mockedStorage = mock(ReferenceStorage.class);
        parsedMethod.setReferenceStorage(mockedStorage);
        String otherClassName = "name.other.OtherClassName";
        String fieldName = "bar";

        FieldReference reference = parsedMethod.addReferenceToField(otherClassName, fieldName);

        assertEquals(fullyQualifiedName, reference.getReferredClassName());
        assertEquals(otherClassName, reference.getReferencingClassName());
        assertEquals(fieldName, reference.getReferredFieldName());

        verify(mockedStorage, times(1)).add(reference);
    }

    @Test
    public void canClone() {
        String methodSignature = "foo()";
        String namePrefix = "name.prefix.ClassName";
        String className = "ClassName";
        String fullyQualifiedName = "name.prefix.ClassName.foo()";
        String path = "C:/File/path.java";
        ParsedMethod parsedMethod = new ParsedMethod(namePrefix, methodSignature, path, className);

        assertEquals(methodSignature, parsedMethod.getName());
        assertEquals(fullyQualifiedName, parsedMethod.getFullyQualifiedName());
        assertEquals(path, parsedMethod.getFilePath());
        assertEquals(className, parsedMethod.getClassName());

        CodeData clonedMethod = parsedMethod.clone();

        assertEquals(methodSignature, clonedMethod.getName());
        assertEquals(fullyQualifiedName, clonedMethod.getFullyQualifiedName());
        assertEquals(path, clonedMethod.getFilePath());
        assertEquals(className, parsedMethod.getClassName());
    }
}
