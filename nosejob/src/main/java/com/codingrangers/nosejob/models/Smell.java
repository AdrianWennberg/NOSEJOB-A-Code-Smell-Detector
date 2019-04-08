package com.codingrangers.nosejob.models;

public interface Smell {
	void setCodeData(CodeData codeData);

	CodeData getLocation();

	boolean isSmelly();

	float getSmellSeverity();
}
