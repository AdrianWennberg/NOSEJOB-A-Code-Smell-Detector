package com.codingrangers.nosejob.models;

import java.util.List;

public interface SmellReport {
	String getSmellName();

	int getTotalSmellSeverity();

	List<SmellInstance> getSmellInstances();
}
