package com.codingrangers.nosejob.parser.data;

import com.codingrangers.nosejob.models.CodeData;

/**
 * NamedBlock TODO: Need to unit test this
 */
public abstract class ParsedCodeUnit extends ParsedCodeLocation implements CodeData {

    private String namePrefix;
    private String name;

    ParsedCodeUnit(String blockNamePrefix, String blockName, String filePath) {
        super(filePath);
        namePrefix = blockNamePrefix;
        name = blockName;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getFullyQualifiedName() {
        return namePrefix + "." + name;
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
