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
<<<<<<< HEAD
		
		for(String methodName : classData.getMethodNames()) {
=======
		for(String methodName : classData.getMethodSignatures()) {
>>>>>>> 679fce3a4a6ed2f8b528f504ec15c96d76d5b043
			System.out.println("method:" + methodName);
			for(VariableData variable : classData.getMethod(methodName).getLocalVariables()) {
				System.out.println("instance var:" + variable.getName());
			}
		}
		
	}
	
}
