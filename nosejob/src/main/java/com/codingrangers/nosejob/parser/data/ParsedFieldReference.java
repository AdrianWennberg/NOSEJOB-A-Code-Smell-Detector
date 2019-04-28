package com.codingrangers.nosejob.parser.data;

import com.codingrangers.nosejob.models.FieldReference;

public class ParsedFieldReference extends ParsedCodeLocation implements FieldReference {

    private String referencingClassName;
    private String referredClassName;
    private String fieldName;

    ParsedFieldReference(String filePath, String referencingClassName, String referredClassName, String fieldName) {
        super(filePath);
        this.referencingClassName = referencingClassName;
        this.referredClassName = referredClassName;
        this.fieldName = fieldName;
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
        return referencingClassName.equals(referredClassName);
    }

}
