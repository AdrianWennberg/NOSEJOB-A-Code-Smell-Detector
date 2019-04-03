package com.codingrangers.nosejob.parser;

import org.junit.*;
import static org.mockito.Mockito.*;

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
		try {
			target.parseProject("ParserTestTargets/ProjectParserTestsFolderTarget/dumyClass1.java");
		}
		catch(ParseFailedException e) {
			assert(false);
		}
		
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
			target.parseProject("ParserTestTargets/ProjectParserTestsFolderTarget");
		} catch (ParseFailedException e) {
			e.printStackTrace();
		}
	
		verify(mockParsedProject, times(2)).addClass(any(ParsedClass.class));
		
	}
	
}
