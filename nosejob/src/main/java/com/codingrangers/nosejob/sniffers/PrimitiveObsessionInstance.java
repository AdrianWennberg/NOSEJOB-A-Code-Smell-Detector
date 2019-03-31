package com.codingrangers.nosejob.smells;

import com.codingrangers.nosejob.helpers.DataStructureHelpers;
import com.codingrangers.nosejob.models.*;

import java.util.ArrayList;

public class PrimitiveObsession implements CodeSniffer {
    private static final String NAME = "PRIMITIVE_OBSESSION";
    private int severity;

    public PrimitiveObsession(){
        severity = 0;
    }

    public int getSeverityInMethodParameters(MethodData dataOfMethod){
        return dataOfMethod.getPrimitiveParameterCount();
    }

    public int getSeverityInMethodReturnTypes(MethodData dataOfMethod){
        return ;
    }

    public int getSeverityInFieldsTypes(ClassData data){
        ArrayList<VariableData> fields = new ArrayList<>();
        for(String fieldName : data.getFieldsNames()){
            fields.add(data.getField(fieldName));
        }
        return DataStructureHelpers.countPrimitives(fields);
    }

    public SmellReport analyzeCode(ProjectData data) {
        return null;
    }
}
