/* NOSEJOB by The Coding Rangers
 * William Akinsanya
 * Alessandro Baccin
 * Peter Major
 * Adrian Wennberg
 * For the UCD module:
 * 	 Software Engineering Project 3
 */
 package com.codingrangers.nosejob.parser;

import com.codingrangers.nosejob.models.ProjectData;
import com.codingrangers.nosejob.parser.data.ParsedClass;
import com.codingrangers.nosejob.parser.data.ParsedProject;
import com.codingrangers.nosejob.parser.visitors.ClassVisitor;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.mockito.Mockito.*;

public class ProjectParserTests {
	private ProjectParser target;
	private ParsedProject mockParsedProject;
	private ClassVisitor mockClassVisitor;

	@Before
	public void before() {
		mockParsedProject =  mock(ParsedProject.class);
		mockClassVisitor = mock(ClassVisitor.class);

		target = new ProjectParser(mockParsedProject, mockClassVisitor);
	}

	@Test
	public void parseProjectTest() {
		ProjectData result = null;

		ParsedClass mockedClass = mock(ParsedClass.class);

		when(mockParsedProject.createClass(anyString(), anyString(), anyString())).thenReturn(mockedClass);

		try {
			result = target.parseProject("src/test/ParserTestTargets/ProjectParserTestsFolderTarget/dumyClass1.java");
		}
		catch(FileNotFoundException e) {
			assert(false);
		}

		assert(result != null);
		verify(mockParsedProject).createClass(anyString(), anyString(), anyString());
		verify(mockClassVisitor).visit(any(ClassOrInterfaceDeclaration.class), eq(mockedClass));
	}

	@Test
	public void parseProject_IncorrectFileTest() {
		try {
			target.parseProject("not/a/file");
			assert(false);
		}catch(Exception e) {

		}
	}

	@Test
	public void DirectoryExplorationTest() {
		when(mockParsedProject.createClass(anyString(), anyString(), anyString())).thenReturn(mock(ParsedClass.class));
		try {
			target.parseProject("src/test/ParserTestTargets/ProjectParserTestsFolderTarget");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		verify(mockParsedProject, times(3)).createClass(anyString(), anyString(), anyString());

	}

	@Test
	public void MultipleRunTest() {
		ProjectData result1 = null;
		ProjectData result2 = null;

		when(mockParsedProject.createClass(anyString(), anyString(), anyString())).thenReturn(mock(ParsedClass.class));

		try {
			result1 = target.parseProject("src/test/ParserTestTargets/ProjectParserTestsFolderTarget");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			result2 = target.parseProject("src/test/ParserTestTargets/ProjectParserTestsFolderTarget");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		assert(result1 != result2);

	}

}
