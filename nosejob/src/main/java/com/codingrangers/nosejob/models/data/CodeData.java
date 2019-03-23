package com.codingrangers.nosejob.models.data;

public interface CodeData {
	String getName();

	String getFullyQualifiedName();

	String getFilePath();

	int getStartLineNumber();

	int getEndLineNumber();

	int getLineCount();
}
