package com.codingrangers.nosejob.models;

import java.io.FileNotFoundException;

import com.codingrangers.nosejob.models.ProjectData;

public interface CodeParser {
	ProjectData parseProject(String path) throws FileNotFoundException;
}
