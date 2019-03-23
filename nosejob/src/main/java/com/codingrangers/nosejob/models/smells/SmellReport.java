package com.codingrangers.nosejob.models.smells;

import java.util.List;

public interface SmellReport {
	String getSmellName();

	int getTotalSmellSeverity();

	List<SmellInstance> getSmellInstances();
}
