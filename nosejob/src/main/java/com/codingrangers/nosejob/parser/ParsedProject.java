package com.codingrangers.nosejob.parser;

import java.util.*;

import com.codingrangers.nosejob.models.IProjectData;
import com.codingrangers.nosejob.models.IClassData;

/**
 * ParsedProject TODO: Need to unit test this
 */
public class ParsedProject implements IProjectData {

	private Map<String, IClassData> classes;

	public ParsedProject() {
		classes = new HashMap<>();
	}

	public void addClass(IClassData newClass) {
		classes.put(newClass.getName(), newClass);
	}

	@Override
	public List<String> getClassNames() {
		return new ArrayList<>(classes.keySet());
	}

	@Override
	public IClassData getClassData(String className) {
		return classes.get(className);
	}
}
