package com.codingrangers.nosejob.models;

public interface ICodeDataAnalyser {
	void setCodeDataToAnalyse(ICodeData codeData);

	ISmellReport retrieveSmellReport();
}
