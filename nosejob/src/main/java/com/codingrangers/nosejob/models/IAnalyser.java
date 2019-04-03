package com.codingrangers.nosejob.models;

public interface IAnalyser {
	void setCodeData(ICodeData codeData);

	ISmellReport getSmellReport();
}
