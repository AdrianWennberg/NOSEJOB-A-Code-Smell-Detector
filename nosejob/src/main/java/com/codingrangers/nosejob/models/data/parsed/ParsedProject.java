package com.codingrangers.nosejob.models.data.parsed;

import java.util.*;
import com.codingrangers.nosejob.models.data.*;

/**
 * ParsedProject
 * TODO: Need to unit test this
 */
public class ParsedProject implements ProjectData {

    private Map<String, ClassData> classes;

    ParsedProject(){
        classes = new HashMap<>();
    }

    public void addClass(ClassData newClass){
        classes.put(newClass.getName(), newClass);
    }

    @Override
    public List<String> getClassNames() {
        return new ArrayList<>(classes.keySet());
    }

    @Override
    public ClassData getClassData(String className) {
        return classes.get(className);
    } 
}