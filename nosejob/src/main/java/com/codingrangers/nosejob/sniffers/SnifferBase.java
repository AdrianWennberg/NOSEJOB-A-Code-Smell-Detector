package com.codingrangers.nosejob.sniffers;

import com.codingrangers.nosejob.models.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class SnifferBase implements ICodeSniffer{

    protected Map<String, IAnalyser> analysers;

    public SnifferBase() {
        analysers = new HashMap<>();
    }

    @Override
    public List<ISmell> analyzeCode(IProjectData currentProject) {
        List<ISmell> result = new ArrayList<>();

        for(IAnalyser analyzer: analysers.values()) {
            for(String className : currentProject.getClassNames()) {
                analyzer.setCodeData(currentProject.getClassData(className));
                result.addAll(analyzer.getSmellInstances());
            }
        }

        return result;
    }
}

