package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.models.ISmell;
import com.codingrangers.nosejob.models.ISmellReport;

import java.util.List;

public class PrimitiveObsessionReport implements ISmellReport {
    private static final String NAME = "Primitive Obsession";

    @Override
    public String getSmellName() {
        return NAME;
    }

    @Override
    public float getTotalSmellSeverity() {
        int totalSeverity = 0;

        for(ISmell smells : getSmellsWithinProject()){
            totalSeverity += smells.getSmellSeverity();
        }

        return totalSeverity;
    }

    @Override
    public List<ISmell> getSmellsWithinProject() {
        return null;
    }
}
