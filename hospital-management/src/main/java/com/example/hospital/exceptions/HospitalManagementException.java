package com.example.hospital.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class HospitalManagementException extends RuntimeException {
    public HospitalManagementException(String message) {
        super(message);
    }
}