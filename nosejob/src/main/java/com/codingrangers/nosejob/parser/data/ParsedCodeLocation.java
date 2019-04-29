/* NOSEJOB by The Coding Rangers
 * William Akinsanya
 * Alessandro Baccin
 * Peter Major
 * Adrian Wennberg
 * For the UCD module:
 * 	 Software Engineering Project 3
 */
 package com.codingrangers.nosejob.parser.data;

import com.codingrangers.nosejob.models.CodeLocation;

public abstract class ParsedCodeLocation implements CodeLocation {

	private int startLine = -1;
	private int endLine = -1;

    private String path;

    ParsedCodeLocation(String filePath) {
		path = filePath;
	}

    public void setStartLine(int lineNumber) {
		if (lineNumber < 0)
			throw new IllegalArgumentException("Start line number must be positive");

		if (endLine != -1 && lineNumber > endLine)
			throw new IllegalArgumentException("Start line must be less than end line");

		if (startLine != -1)
            throw new IllegalStateException("Start line has already been set");

		startLine = lineNumber;
	}

    public void setEndLine(int lineNumber) {
		if (lineNumber < 0)
			throw new IllegalArgumentException("End line number must be positive");

		if (startLine != -1 && lineNumber < startLine)
			throw new IllegalArgumentException("End line must be less than start line");

		if (endLine != -1)
            throw new IllegalStateException("End line has already been set");

		endLine = lineNumber;
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

    @Override
    public String getFilePath() {
        return path;
    }

    void setFilePath(String path) {
        this.path = path;
    }
}
