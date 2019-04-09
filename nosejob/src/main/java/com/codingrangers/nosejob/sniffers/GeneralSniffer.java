package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.models.ProjectData;
import com.codingrangers.nosejob.models.Smell;
import com.codingrangers.nosejob.models.Sniffer;

import java.util.ArrayList;
import java.util.List;

public abstract class GeneralSniffer implements Sniffer {
    protected ProjectData currentProjectToAnalyse;
    protected List<Smell> smells;

    public GeneralSniffer() {
        this.smells = new ArrayList<>();
    }

    @Override
    public void setProjectToSniff(ProjectData projectData) {
        this.currentProjectToAnalyse = projectData;
    }
}
