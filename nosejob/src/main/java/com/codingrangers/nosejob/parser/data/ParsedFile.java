/* NOSEJOB by The Coding Rangers
 * William Akinsanya
 * Alessandro Baccin
 * Peter Major
 * Adrian Wennberg
 * For the UCD module:
 * 	 Software Engineering Project 3
 */
 package com.codingrangers.nosejob.parser.data;

import java.util.Stack;

public class ParsedFile {
    private ParsedProject fileProject;
    private String filePath;
    private String packageName;

    private Stack<ParsedClass> createdClasses;

    public ParsedFile(ParsedProject fileProject, String packageName, String filePath) {
        this.fileProject = fileProject;
        this.packageName = packageName;
        this.filePath = filePath;

        createdClasses = new Stack<>();
    }


    public ParsedClass createClass(String className, int startLineNumber, int endLineNumber) {
        while (!createdClasses.empty() && startLineNumber > createdClasses.peek().getEndLineNumber()) {
            createdClasses.pop();
        }
        ParsedClass newClass;
        if (!createdClasses.empty()) {
            String classPackage = createdClasses.peek().getFullyQualifiedName();

            newClass = fileProject.createClass(classPackage, className, filePath);
        } else {
            newClass = fileProject.createClass(packageName, className, filePath);
        }

        newClass.setStartLine(startLineNumber);
        newClass.setEndLine(endLineNumber);

        createdClasses.push(newClass);
        return newClass;
    }

}
