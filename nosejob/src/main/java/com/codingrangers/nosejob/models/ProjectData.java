package com.codingrangers.nosejob.models;

import java.util.List;

public interface ProjectData {
	List<String> getClassNames();

	ClassData getClassData(String className);
}
