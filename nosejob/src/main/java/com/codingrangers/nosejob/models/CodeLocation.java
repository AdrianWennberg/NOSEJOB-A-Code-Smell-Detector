/* NOSEJOB by The Coding Rangers
 * William Akinsanya
 * Alessandro Baccin
 * Peter Major
 * Adrian Wennberg
 * For the UCD module:
 * 	 Software Engineering Project 3
 */
 package com.codingrangers.nosejob.models;

public interface CodeLocation {

	int getStartLineNumber();

	int getEndLineNumber();

	int getLineCount();

	String getFilePath();
}
