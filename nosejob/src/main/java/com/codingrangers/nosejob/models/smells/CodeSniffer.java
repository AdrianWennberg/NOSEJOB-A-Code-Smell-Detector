package com.codingrangers.nosejob.models.smells;

import com.codingrangers.nosejob.models.data.ProjectData;

public interface CodeSniffer {
	SmellReport analyzeCode(ProjectData data);
}
