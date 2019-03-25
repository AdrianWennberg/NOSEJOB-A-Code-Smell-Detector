package com.codingrangers.nosejob.models.data.parsed;

import com.codingrangers.nosejob.models.data.VariableData;

/**
 * ParsedVariable
 */
public class ParsedVariable implements VariableData {

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getFullyQualifiedName() {
        return null;
    }

    @Override
    public String getFilePath() {
        return null;
    }

    @Override
    public int getStartLineNumber() {
        return 0;
    }

    @Override
    public int getEndLineNumber() {
        return 0;
    }

    @Override
    public int getLineCount() {
        return 0;
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public boolean isPrimitive() {
        return false;
    }
}