package com.sn.collaborationwebapp.exception;
public class CompanyNameNotFoundException extends RuntimeException {
    private final String resourceName;
    private final String fieldName;
    private final String name;

    public CompanyNameNotFoundException(String resourceName, String fieldName, String name) {
        super(String.format("%s not found with %s: %s", resourceName, fieldName, name));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.name = name;
    }
}