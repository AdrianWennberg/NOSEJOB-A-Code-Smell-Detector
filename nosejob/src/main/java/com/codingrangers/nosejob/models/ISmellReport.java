package com.codingrangers.nosejob.models;

import java.util.List;

public interface ISmellReport {
    void setSmellName(String smellName);

    void addSmell(ISmell smell);

    void addSmells(List<ISmell> smells);

    String getSmellName();

    List<ISmell> getSmells();

    float getTotalSmellSeverity();
}
