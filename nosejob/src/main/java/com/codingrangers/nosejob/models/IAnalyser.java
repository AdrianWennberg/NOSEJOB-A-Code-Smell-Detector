package com.codingrangers.nosejob.models;

import java.util.List;

public interface IAnalyser {
	void setCodeData(ICodeData codeData);

	String getSmellName();

	float getTotalSmellSeverity();

	List<ISmell> getSmellInstances();
}
