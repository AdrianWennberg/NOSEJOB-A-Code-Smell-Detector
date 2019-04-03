package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.models.IAnalyser;
import com.codingrangers.nosejob.models.IProjectReport;
import com.codingrangers.nosejob.models.ISmellReport;

import java.util.ArrayList;
import java.util.List;

public class GlobalSniffer extends SnifferBase implements IProjectReport {
    public GlobalSniffer(){
        PrimitiveObsessionAnalyser primitiveObsessionAnalyser = new PrimitiveObsessionAnalyser();
        this.analysers.put(primitiveObsessionAnalyser.getSmellName(), primitiveObsessionAnalyser);
    }

    @Override
    /**TODO Maybe an average?*/
    public float getProjectScore() {
//        int projectScore = 0;
//        for(IAnalyser analyser : analysers.values()){
//            projectScore += analyser.getTotalSmellSeverity();
//        }
        return 0;
    }

    @Override
    public List<String> getSmellNames() {
        List<String> smellNames = new ArrayList<>();
        for(String smellName : analysers.keySet()){
            smellNames.add(smellName);
        }
        return smellNames;
    }

    @Override
    public ISmellReport getSmellReport(String smellName) {
        return analysers.get(smellName).getSmellReport();
    }
}
