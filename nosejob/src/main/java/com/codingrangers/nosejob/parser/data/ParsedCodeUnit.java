package com.codingrangers.nosejob.parser.data;

import com.codingrangers.nosejob.models.CodeData;

public abstract class ParsedCodeUnit extends ParsedCodeLocation implements CodeData {

    private String namePrefix;
    private String name;
    private String separator = ".";

    ParsedCodeUnit(String blockNamePrefix, String blockName, String filePath) {
        super(filePath);
        namePrefix = blockNamePrefix;
        name = blockName;
    }

    public void SetNameSeparator(String symbol) {
        separator = symbol;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getFullyQualifiedName() {
        if ("".equals(namePrefix))
            return name;

        return namePrefix + separator + name;
    }

    String getNamePrefix() {
        return namePrefix;
    }

    void setName(String name) {
        this.name = name;
    }

    void setNamePrefix(String namePrefix) {
        this.namePrefix = namePrefix;
    }
}
