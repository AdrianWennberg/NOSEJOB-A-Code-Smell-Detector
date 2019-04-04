package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.models.ISmell;
import com.codingrangers.nosejob.models.ISmellReport;

import java.util.ArrayList;
import java.util.List;

public class SmellReport implements ISmellReport {
    public String smellName;
    private List<ISmell> smells;

    public SmellReport(){
        smells = new ArrayList<>();
    }

    @Override
    public void setSmellName(String smellName) {
        this.smellName = smellName;
    }

    @Override
    public String getSmellName() {
        return smellName;
    }

    @Override
    public void addSmell(ISmell smell) {
        smells.add(smell);
    }

    @Override
    public void addSmells(List<ISmell> retrievedSmells) {
        smells.addAll(retrievedSmells);
    }

    @Override
    public List<ISmell> getSmells() {
        return smells;
    }

    @Override
    public float getTotalSmellSeverity() {
        int totalSeverity = 0;

        for(ISmell smells : getSmells()){
            totalSeverity += smells.getSmellSeverity();
        }

        return totalSeverity;
    }
}
