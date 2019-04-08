package com.codingrangers.nosejob.parser;

import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import com.codingrangers.nosejob.models.IClassData;
import org.junit.Test;

public class ParsedProjectTest {
	@Test
	public void canAddClass() {
		String className = "TestClass";
		IClassData mockedClass = mock(IClassData.class);
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
		IClassData firstMockedClass = mock(IClassData.class);
		when(firstMockedClass.getName()).thenReturn(firstClassName);

		String secondClassName = "testClassTwo";
		IClassData secondMockedClass = mock(IClassData.class);
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
