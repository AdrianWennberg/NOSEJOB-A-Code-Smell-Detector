package com.codingrangers.nosejob.models;

import com.codingrangers.nosejob.models.ProjectData;

public interface CodeParser {
	ProjectData parseProject(String path);
}
