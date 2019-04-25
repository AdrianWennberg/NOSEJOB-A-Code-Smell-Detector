package com.codingrangers.nosejob.models;

import java.util.List;

public interface SmellReportBody {
	void setSmellName(String smellName);

	void addSmell(Smell smell);

	void addSmells(List<Smell> smells);

	String getSmellName();

	List<Smell> getSmells();

	float getTotalSmellSeverity();

	String getDisplayTotalSmellSeverity();
}
