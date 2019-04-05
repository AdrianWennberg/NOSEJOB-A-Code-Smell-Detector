package com.codingrangers.nosejob.reports;

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
        if(smell.equals(null))
            throw new NullPointerException("Cannot add a null as a smell.");

        smells.add(smell);
    }

    @Override
    public void addSmells(List<ISmell> retrievedSmells) {
        if(retrievedSmells.equals(null))
            throw new NullPointerException("Cannot add a null list of smells.");

        smells.addAll(retrievedSmells);
    }

    @Override
    public List<ISmell> getSmells() {
        List<ISmell> smellySmells = new ArrayList<>();

        for (ISmell smell : smells){
            if(smell.isSmelly()) smellySmells.add(smell);
        }

        return smellySmells;
    }

    @Override
    /**TODO*/
    public float getTotalSmellSeverity() {
        if (smells.size() == 0) return 0f;

        float severity_avg = 0f;
        for(ISmell smell : smells){
            severity_avg += smell.getSmellSeverity();
        }
        severity_avg /= smells.size();

        return severity_avg;
    }
}
