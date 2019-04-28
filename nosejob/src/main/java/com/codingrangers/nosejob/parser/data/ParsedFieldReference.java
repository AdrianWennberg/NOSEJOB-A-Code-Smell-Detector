package com.codingrangers.nosejob.parser.data;

import com.codingrangers.nosejob.models.FieldReference;

public class ParsedFieldReference extends ParsedCodeLocation implements FieldReference {

    private final String referencingClassName;
    private final String referredClassName;
    private final String fieldName;
    private final boolean internal;

    ParsedFieldReference(String filePath,
                         String referencingClassName,
                         String referredClassName,
                         String fieldName,
                         boolean internal) {
        super(filePath);
        this.referencingClassName = referencingClassName;
        this.referredClassName = referredClassName;
        this.fieldName = fieldName;
        this.internal = internal;
    }

    @Override
    public String getReferredClassName() {
        return referredClassName;
    }

    @Override
    public String getReferencingClassName() {
        return referencingClassName;
    }

    @Override
    public String getReferredFieldName() {
        return fieldName;
    }

    @Override
    public boolean isInternal() {
        return internal;
    }

}
