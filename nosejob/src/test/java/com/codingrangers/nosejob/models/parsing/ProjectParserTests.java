package com.codingrangers.nosejob.models.parsing;

import org.junit.*;

import com.codingrangers.nosejob.models.data.*;

public class ProjectParserTests {

	@Test
	public void parseProjectTest() {
		CodeParser parser = new ProjectParser();

		ProjectData projectdata = parser.parseProject("src/test/java/com/codingrangers/nosejob/models/parsing/testTargets/VariableIdentificationTarget.java");
		ClassData classData = projectdata.getClassData("VariableIdentificationTarget");
		
		System.out.println("start");
		for(String name : classData.getFieldsNames()) {
			System.out.println("field:" + name);
		}
		System.out.println("done");
		for(String methodName : classData.getMethodSignatures()) {
			System.out.println("method:" + methodName);
			for(VariableData variable : classData.getMethod(methodName).getLocalVariables()) {
				System.out.println("instance var:" + variable.getName());
			}
		}
		
	}
	
}
