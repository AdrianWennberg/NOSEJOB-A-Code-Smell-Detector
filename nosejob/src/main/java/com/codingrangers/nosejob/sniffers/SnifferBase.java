package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.models.*;

import java.util.ArrayList;
import java.util.List;

public abstract class SnifferBase implements ICodeSniffer{

    protected List<IAnalyser> analyzers;

    public SnifferBase() {
        analyzers = new ArrayList<>();
    }

    public List<ISmell> analyzeCode(IProjectData data) {
        List<ISmell> result = new ArrayList<>();

        for(IAnalyser analyzer: analyzers) {
            for(String className : data.getClassNames()) {
                analyzer.setCodeData(data.getClassData(className));
                result.addAll(analyzer.getSmellInstances());
            }
        }
        return result;
    }
}

