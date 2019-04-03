package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.models.IAnalyser;
import com.codingrangers.nosejob.models.IProjectReport;

import java.util.ArrayList;
import java.util.List;

/**TODO change name to something more appropriate when more analysers are added*/
public class PrimitiveObsessionSniffer extends SnifferBase implements IProjectReport {
    public PrimitiveObsessionSniffer(){
        PrimitiveObsessionAnalyser primitiveObsessionAnalyser = new PrimitiveObsessionAnalyser();
        this.analysers.put(primitiveObsessionAnalyser.getSmellName(), primitiveObsessionAnalyser);
    }

    @Override
    /**TODO Maybe an average?*/
    public float getProjectScore() {
        int projectScore = 0;
        for(IAnalyser analyser : analysers.values()){
            projectScore += analyser.getTotalSmellSeverity();
        }
        return projectScore;
    }

    @Override
    public List<String> getSmellNames() {
        List<String> smellNames = new ArrayList<>();
        for(IAnalyser analyser : analysers.values()){
            smellNames.add(analyser.getSmellName());
        }
        return smellNames;
    }

    @Override
    public IAnalyser getSmellReport(String smellName) {
        return analysers.get(smellName);
    }
}
