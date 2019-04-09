package com.codingrangers.nosejob.parser.data;

import com.codingrangers.nosejob.models.MethodReference;

public class ParsedMethodReference extends ParsedCodeLocation implements MethodReference {

    private String referencingClassName;
    private String referredClassName;
    private String methodSignature;

    ParsedMethodReference(String filePath, String referencingClassName, String referredClassName, String methodSignature) {
        super(filePath);
        this.referencingClassName = referencingClassName;
        this.referredClassName = referredClassName;
        this.methodSignature = methodSignature;
    }

    @Override
    public String getReferredClassName() {
        return referencingClassName;
    }

    @Override
    public String getReferencingClassName() {
        return referredClassName;
    }

    @Override
    public String getReferredMethodSignature() {
        return methodSignature;
    }

    @Override
    public boolean isInternal() {
        return referredClassName.equals(referencingClassName);
    }

}
