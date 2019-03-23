package com.codingrangers.nosejob.models.smells;

import com.codingrangers.nosejob.models.data.CodeData;

public interface SmellInstance {
	CodeData getLocation();

	int getSmellSeverity();
}
