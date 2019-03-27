package com.codingrangers.nosejob.models;

import com.codingrangers.nosejob.models.ProjectData;

public interface ProjectAnalyser {
	ProjectReport analyzeCode(ProjectData data);
}
