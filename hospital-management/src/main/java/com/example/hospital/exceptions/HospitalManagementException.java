// HospitalManagementException.java
package com.example.hospital.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class HospitalManagementException extends RuntimeException {
    private final String errorCode;

    public HospitalManagementException(String message) {
        this(message, "HME-500");
    }

    public HospitalManagementException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

}