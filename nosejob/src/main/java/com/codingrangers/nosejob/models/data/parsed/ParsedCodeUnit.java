package com.codingrangers.nosejob.models.data.parsed;

import com.codingrangers.nosejob.models.data.CodeData;

/**
 * NamedBlock
 * TODO: Need to unit test this
 */
public abstract class ParsedCodeUnit implements CodeData {

    private String namePrefix;
    private String name;
    private String path;
    private int startLine = 0;
    private int endLine = 0;

    public ParsedCodeUnit(String blockNamePrefix, String blockName, String filePath) {
        namePrefix = blockNamePrefix;
        name = blockName;
        path = filePath;
    }

    public void setStartLine(int lineNumber) {
        startLine = lineNumber;
    }
    
    public void setEndLine(int lineNumber) {
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
        return endLine - startLine;
    }
}