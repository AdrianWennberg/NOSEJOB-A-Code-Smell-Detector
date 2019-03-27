package com.codingrangers.nosejob.models;

import com.codingrangers.nosejob.models.ProjectData;

public interface CodeSniffer {
	SmellReport analyzeCode(ProjectData data);
}
