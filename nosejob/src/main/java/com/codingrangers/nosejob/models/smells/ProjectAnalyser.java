package com.codingrangers.nosejob.models.smells;

import com.codingrangers.nosejob.models.data.ProjectData;

public interface ProjectAnalyser {
	ProjectReport analyzeCode(ProjectData data);
}
