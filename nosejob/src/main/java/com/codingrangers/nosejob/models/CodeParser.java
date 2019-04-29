/* NOSEJOB by The Coding Rangers
 * William Akinsanya
 * Alessandro Baccin
 * Peter Major
 * Adrian Wennberg
 * For the UCD module:
 * 	 Software Engineering Project 3
 */
 package com.codingrangers.nosejob.models;

import java.io.FileNotFoundException;

public interface CodeParser {
	ProjectData parseProject(String path) throws FileNotFoundException;
}
