package com.codingrangers.nosejob.parser.data;

import com.codingrangers.nosejob.models.ClassData;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParsedProjectTest {
    @Test
    public void canCreateMockedClass() {
        String className = "testClass";
        ParsedClass mockedClass = mock(ParsedClass.class);
        when(mockedClass.getName()).thenReturn(className);

        ParsedProject parsedProject = new ParsedProject();

        ParsedClass mockedPrototype = mock(ParsedClass.class);
        when(mockedPrototype.clone()).thenReturn(mockedClass);

        parsedProject.setClassPrototype(mockedPrototype);
        parsedProject.createClass("", className, "");

        List<String> classes = parsedProject.getClassNames();

        assertEquals(1, classes.size());
        assertEquals(className, classes.get(0));
        assertEquals(mockedClass, parsedProject.getClassData(className));
    }

    @Test
    public void canCreateMultipleClasses() {
        String firstClassName = "testClassOne";
        ParsedClass firstMockedClass = mock(ParsedClass.class);
        when(firstMockedClass.getName()).thenReturn(firstClassName);

        String secondClassName = "testClassTwo";
        ParsedClass secondMockedClass = mock(ParsedClass.class);
        when(secondMockedClass.getName()).thenReturn(secondClassName);


        ParsedProject parsedProject = new ParsedProject();

        ParsedClass mockedPrototype = mock(ParsedClass.class);
        parsedProject.setClassPrototype(mockedPrototype);

        when(mockedPrototype.clone()).thenReturn(firstMockedClass);
        parsedProject.createClass("", "", "");
        when(mockedPrototype.clone()).thenReturn(secondMockedClass);
        parsedProject.createClass("", "", "");

        List<String> classes = parsedProject.getClassNames();

        assertEquals(2, classes.size());
        assertThat(classes, containsInAnyOrder(firstClassName, secondClassName));
        assertEquals(firstMockedClass, parsedProject.getClassData(firstClassName));
        assertEquals(secondMockedClass, parsedProject.getClassData(secondClassName));
    }

    @Test
    public void canCreateDefaultClass() {
        String classNamePrefix = "com.example";
        String className = "testClass";
        String filePath = "C:/Test/File/Path/";

        String fullyQualifiedClassName = "com.example.testClass";

        ParsedProject parsedProject = new ParsedProject();
        ClassData createdClassData = parsedProject.createClass(classNamePrefix, className, filePath);


        List<String> classes = parsedProject.getClassNames();

        assertEquals(1, classes.size());
        assertEquals(className, classes.get(0));

        assertEquals(createdClassData, parsedProject.getClassData(className));

        assertEquals(fullyQualifiedClassName, createdClassData.getFullyQualifiedName());
        assertEquals(className, createdClassData.getName());
        assertEquals(filePath, createdClassData.getFilePath());
    }

    @Test
    public void canCreateMultipleDefaultClasses() {

        String firstClassNamePrefix = "com.example";
        String firstClassName = "testClassOne";
        String firstFilePath = "C:/Test/File/Path/";
        String firstFullyQualifiedClassName = "com.example.testClassOne";

        String secondClassNamePrefix = "com.example.other";
        String secondClassName = "testClassTwo";
        String secondFilePath = "C:/Test/File/Path/other/";
        String secondFullyQualifiedClassName = "com.example.other.testClassTwo";


        ParsedProject parsedProject = new ParsedProject();
        ClassData firstCreatedClassData = parsedProject.createClass(firstClassNamePrefix, firstClassName, firstFilePath);
        ClassData secondCreatedClassData = parsedProject.createClass(secondClassNamePrefix, secondClassName, secondFilePath);

        List<String> classes = parsedProject.getClassNames();

        assertEquals(2, classes.size());
        assertThat(classes, containsInAnyOrder(firstClassName, secondClassName));
        assertEquals(firstCreatedClassData, parsedProject.getClassData(firstClassName));
        assertEquals(secondCreatedClassData, parsedProject.getClassData(secondClassName));


        assertEquals(firstFullyQualifiedClassName, firstCreatedClassData.getFullyQualifiedName());
        assertEquals(firstClassName, firstCreatedClassData.getName());
        assertEquals(firstFilePath, firstCreatedClassData.getFilePath());

        assertEquals(secondFullyQualifiedClassName, secondCreatedClassData.getFullyQualifiedName());
        assertEquals(secondClassName, secondCreatedClassData.getName());
        assertEquals(secondFilePath, secondCreatedClassData.getFilePath());
    }


}
