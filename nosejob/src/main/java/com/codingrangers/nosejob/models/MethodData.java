/* NOSEJOB by The Coding Rangers
 * William Akinsanya
 * Alessandro Baccin
 * Peter Major
 * Adrian Wennberg
 * For the UCD module:
 * 	 Software Engineering Project 3
 */
 package com.codingrangers.nosejob.models;

import java.util.List;

public interface MethodData extends CodeData {
	String getClassName();

	VariableData getReturnType();

	boolean hasPrimitiveReturnType();

	List<VariableData> getParameters();

	List<VariableData> getLocalVariables();

	int getPrimitiveParameterCount();

	int getPrimitiveLocalCount();
}
