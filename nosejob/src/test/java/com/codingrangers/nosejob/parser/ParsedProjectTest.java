package com.codingrangers.nosejob.parser;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;

import com.codingrangers.nosejob.models.ClassData;
import com.codingrangers.nosejob.parser.ParsedProject;

public class ParsedProjectTest {
	@Test
	public void canAddClass() {
		String className = "TestClass";
		ClassData mockedClass = mock(ClassData.class);
		ParsedProject parsedProject = new ParsedProject();

		when(mockedClass.getName()).thenReturn(className);

		parsedProject.addClass(mockedClass);

		List<String> classes = parsedProject.getClassNames();

		assertEquals(1, classes.size());
		assertEquals(className, classes.get(0));
		assertEquals(mockedClass, parsedProject.getClassData(className));
	}

	@Test
	public void canAddMultipleClasses() {
		String firstClassName = "testClassOne";
		ClassData firstMockedClass = mock(ClassData.class);
		when(firstMockedClass.getName()).thenReturn(firstClassName);

		String secondClassName = "testClassTwo";
		ClassData secondMockedClass = mock(ClassData.class);
		when(secondMockedClass.getName()).thenReturn(secondClassName);

		ParsedProject parsedProject = new ParsedProject();

		parsedProject.addClass(firstMockedClass);
		parsedProject.addClass(secondMockedClass);

		List<String> classes = parsedProject.getClassNames();

		assertEquals(2, classes.size());
		assertThat(classes, containsInAnyOrder(firstClassName, secondClassName));
		assertEquals(firstMockedClass, parsedProject.getClassData(firstClassName));
		assertEquals(secondMockedClass, parsedProject.getClassData(secondClassName));
	}
}
