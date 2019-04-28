package com.codingrangers.nosejob.parser.data;

import com.codingrangers.nosejob.models.MethodReference;

public class ParsedMethodReference extends ParsedCodeLocation implements MethodReference {

    private String referencingClassName;
    private String referredClassName;
    private String methodSignature;
    private boolean internal;

    ParsedMethodReference(String filePath,
                          String referencingClassName,
                          String referredClassName,
                          String methodSignature,
                          boolean internal) {
        super(filePath);
        this.referencingClassName = referencingClassName;
        this.referredClassName = referredClassName;
        this.methodSignature = methodSignature;
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
    public String getReferredMethodSignature() {
        return methodSignature;
    }

    @Override
    public boolean isInternal() {
        return internal;
    }
}
