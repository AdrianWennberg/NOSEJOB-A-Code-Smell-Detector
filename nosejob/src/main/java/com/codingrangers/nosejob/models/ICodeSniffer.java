package com.codingrangers.nosejob.models;

import java.util.List;

public interface ICodeSniffer {
	List<ISmellReport> analyzeCode(IProjectData data);
}
