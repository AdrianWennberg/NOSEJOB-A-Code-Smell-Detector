package com.codingrangers.nosejob.parser.data;

import com.codingrangers.nosejob.models.ClassData;
import com.codingrangers.nosejob.models.ProjectData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ParsedProject TODO: Need to unit test this
 */
public class ParsedProject implements ProjectData {

	private Map<String, ClassData> classes;
	private ParsedClass classPrototype;

	public ParsedProject() {
		classes = new HashMap<>();
		classPrototype = new ParsedClass("", "", "");
	}

	public void setClassPrototype(ParsedClass newPrototype) {
		this.classPrototype = newPrototype;
	}

	public ParsedClass createClass(String classNamePrefix, String className, String filePath) {
		ParsedClass newClass = classPrototype.clone();
		newClass.setNamePrefix(classNamePrefix);
		newClass.setName(className);
		newClass.setFilePath(filePath);
		classes.put(newClass.getName(), newClass);
		return newClass;
	}

	@Override
	public List<String> getClassNames() {
		return new ArrayList<>(classes.keySet());
	}

	@Override
	public ClassData getClassData(String className) {
		return classes.get(className);
	}
}
