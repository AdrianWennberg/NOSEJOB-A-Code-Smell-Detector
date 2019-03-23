package com.codingrangers.nosejob.models.parsing;

import com.codingrangers.nosejob.models.data.ProjectData;

public interface CodeParser {
	ProjectData parseProject(String path);
}
