package com.codingrangers.nosejob.models;

import java.util.List;

public interface IProjectData {
	List<String> getClassNames();

	IClassData getClassData(String className);
}
