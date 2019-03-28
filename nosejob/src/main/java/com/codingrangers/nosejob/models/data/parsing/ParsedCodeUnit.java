package com.codingrangers.nosejob.models.data.parsing;

import com.codingrangers.nosejob.models.data.CodeData;

/**
 * NamedBlock TODO: Need to unit test this
 */
public abstract class ParsedCodeUnit implements CodeData {

    private String namePrefix;
    private String name;
    private String path;
    private int startLine = -1;
    private int endLine = -1;

    public ParsedCodeUnit(String blockNamePrefix, String blockName, String filePath) {
        namePrefix = blockNamePrefix;
        name = blockName;
        path = filePath;
    }

    public void setStartLine(int lineNumber) {
    	if( lineNumber < 0)
    		throw new IllegalArgumentException("Start line number must be positive");
    	
    	if(endLine != -1 && lineNumber > endLine)
    		throw new IllegalArgumentException("Start line must be less than end line");
    	
    	if(startLine != -1)
    		throw new IllegalStateException("Start line has allready been set");
    	
        startLine = lineNumber;
    }

    public void setEndLine(int lineNumber) {
    	if( lineNumber < 0)
    		throw new IllegalArgumentException("End line number must be positive");
    	
    	if(startLine != -1 && lineNumber < startLine)
    		throw new IllegalArgumentException("End line must be less than start line");
    	
    	if(endLine != -1)
    		throw new IllegalStateException("End line has allready been set");
    	
        endLine = lineNumber;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getFullyQualifiedName() {
        return namePrefix + "." + name;
    }

    @Override
    public String getFilePath() {
        return path;
    }

    @Override
    public int getStartLineNumber() {
        return startLine;
    }

    @Override
    public int getEndLineNumber() {
        return endLine;
    }

    @Override
    public int getLineCount() {
        return endLine - startLine + 1;
    }
}