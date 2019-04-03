package com.codingrangers.nosejob.models;

import com.codingrangers.nosejob.models.ProjectData;
import com.codingrangers.nosejob.parser.ParseFailedException;

public interface CodeParser {
	ProjectData parseProject(String path) throws ParseFailedException;
}
