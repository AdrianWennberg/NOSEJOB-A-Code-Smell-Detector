package com.codingrangers.nosejob.models.data;

import java.util.*;

/**
 * ParsedProject
 */
public class ParsedProject implements ProjectData {

    Map<String, ClassData> classes;

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