package com.codingrangers.nosejob.models;

public interface FieldReferance extends CodeData {
	String getRefferedClassName();
	String getReferencingClassName();
	String getRefferedFieldName();
	boolean isInternal();
}
