package com.codingrangers.nosejob.models.data;


// TODO: Need to unit test this
public class DataStructureHelpers {
    public static int countPrimitives(Iterable<VariableData> variables) {
        int primitiveCount = 0;
        for (VariableData var : variables) {
            if(var.isPrimitive())
                primitiveCount++;
        }
        return primitiveCount;
    }
}