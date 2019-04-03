package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.models.IAnalyser;
import com.codingrangers.nosejob.models.IProjectReport;

import java.util.List;

/**TODO*/
public class PrimitiveObsessionSniffer extends SnifferBase implements IProjectReport {
    public PrimitiveObsessionSniffer(){
        this.analyzers.add(new PrimitiveObsessionAnalyser());
    }

    @Override
    public int getProjectScore() {
        return 0;
    }

    @Override
    public List<String> getSmellNames() {
        return null;
    }

    @Override
    public IAnalyser getSmellReport(String smellName) {
        return null;
    }
}
