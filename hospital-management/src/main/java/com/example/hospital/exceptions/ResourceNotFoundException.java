// ResourceNotFoundException.java
package com.example.hospital.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends HospitalManagementException {
    private final String resourceName;
    private final Object identifier;

    public ResourceNotFoundException(String resourceName, Object identifier) {
        super(String.format("%s not found with identifier: %s", resourceName, identifier.toString()),
                "HME-404");
        this.resourceName = resourceName;
        this.identifier = identifier;
    }

}