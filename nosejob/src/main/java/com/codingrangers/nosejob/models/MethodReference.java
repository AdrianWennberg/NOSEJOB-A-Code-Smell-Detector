package com.codingrangers.nosejob.models;

public interface MethodReference extends CodeData {
    String getRefferedClassName();
    String getReferencingClassName();
    String getRefferedMethodSignature();
    boolean isInternal();
}