package com.codingrangers.nosejob.models;

public interface FieldReference extends CodeLocation {
	String getReferredClassName();
	String getReferencingClassName();

	String getReferredFieldName();
	boolean isInternal();
}