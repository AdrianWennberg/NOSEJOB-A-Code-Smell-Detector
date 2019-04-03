package com.codingrangers.nosejob.models;

import java.util.List;

public interface ISmellReport {
    String getSmellName();

    float getTotalSmellSeverity();

    List<ISmell> getSmellsWithinProject();
}
