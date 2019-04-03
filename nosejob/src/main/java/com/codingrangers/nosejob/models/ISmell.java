package com.codingrangers.nosejob.models;

public interface ISmell {
	void setCodeData(ICodeData codeData);

	ICodeData getLocation();

	float getSmellSeverity();
}
