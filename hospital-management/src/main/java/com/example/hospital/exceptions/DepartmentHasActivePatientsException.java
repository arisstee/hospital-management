package com.example.hospital.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DepartmentHasActivePatientsException extends HospitalManagementException {
    public DepartmentHasActivePatientsException(Long departmentId) {
        super(String.format("Cannot delete department %d as it has active patients", departmentId));
    }
}
