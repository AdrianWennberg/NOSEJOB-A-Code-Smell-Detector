package com.codingrangers.nosejob.models;

public interface FieldReference extends CodeData {
    String getRefferedClassName();
    String getReferencingClassName();
    String getRefferedFieldName();
    boolean isInternal();
}