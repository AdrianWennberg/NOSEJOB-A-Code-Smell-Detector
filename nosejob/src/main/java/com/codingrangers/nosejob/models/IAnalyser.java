package com.codingrangers.nosejob.models;

import java.util.List;

public interface IAnalyzer {
	void setCodeData(ICodeData codeData);

	String getSmellName();

	int getTotalSmellSeverity();

	List<ISmell> getSmellInstances();
}
