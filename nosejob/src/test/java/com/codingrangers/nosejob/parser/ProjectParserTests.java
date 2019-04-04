package com.codingrangers.nosejob.parser;

import org.junit.*;
import static org.mockito.Mockito.*;

import java.io.FileNotFoundException;

import com.codingrangers.nosejob.models.ProjectData;
import com.codingrangers.nosejob.parser.visitors.ClassVisitor;
import com.github.javaparser.ast.CompilationUnit;	

public class ProjectParserTests {
	ProjectParser target;
	ParsedProject mockParsedProject;
	ClassVisitor mockClassVisitor;
	
	@Before
	public void before() {
		mockParsedProject =  mock(ParsedProject.class);
		mockClassVisitor = mock(ClassVisitor.class);
		
		target = new ProjectParser(mockParsedProject, mockClassVisitor);
	}
	
	@Test
	public void parseProjectTest() {
		ProjectData result = null;
		
		try {
			result = target.parseProject("src/test/ParserTestTargets/ProjectParserTestsFolderTarget/dumyClass1.java");
		}
		catch(FileNotFoundException e) {
			assert(false);
		}
		
		assert(result != null);
		verify(mockParsedProject).addClass(any(ParsedClass.class));
		verify(mockClassVisitor).visit(any(CompilationUnit.class),any(ParsedClass.class));
	}
	
	@Test
	public void parseProject_IncorectFileTest() {
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
	
		verify(mockParsedProject, times(3)).addClass(any(ParsedClass.class));
		
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
