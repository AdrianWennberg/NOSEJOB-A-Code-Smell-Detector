package com.codingrangers.nosejob.models;

public interface MethodReference extends CodeLocation {
	String getReferredClassName();
	String getReferencingClassName();

	String getReferredMethodSignature();
	boolean isInternal();
}
