package com.codingrangers.nosejob.parser;

import com.codingrangers.nosejob.models.ProjectData;
import com.codingrangers.nosejob.parser.data.ParsedClass;
import com.codingrangers.nosejob.parser.data.ParsedProject;
import com.codingrangers.nosejob.parser.visitors.ClassVisitor;
import com.github.javaparser.ast.CompilationUnit;
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

		when(mockParsedProject.createClass(anyString(), anyString(), anyString())).thenReturn(mock(ParsedClass.class));

		try {
			result = target.parseProject("src/test/ParserTestTargets/ProjectParserTestsFolderTarget/dumyClass1.java");
		}
		catch(FileNotFoundException e) {
			assert(false);
		}

		assert(result != null);
		verify(mockParsedProject).createClass(anyString(), anyString(), anyString());
		verify(mockClassVisitor).visit(any(CompilationUnit.class),any(ParsedClass.class));
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
		try {
			target.parseProject("src/test/ParserTestTargets/ProjectParserTestsFolderTarget");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		verify(mockParsedProject, times(3)).createClass(anyString(), anyString(), anyString());

	}

	@Test
	public void MultipulRunTest() {
		ProjectData result1 = null;
		ProjectData result2 = null;

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
