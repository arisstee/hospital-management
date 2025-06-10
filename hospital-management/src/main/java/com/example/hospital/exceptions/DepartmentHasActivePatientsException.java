// DepartmentHasActivePatientsException.java
package com.example.hospital.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.CONFLICT)
public class DepartmentHasActivePatientsException extends HospitalManagementException {
    private final Long departmentId;

    public DepartmentHasActivePatientsException(Long departmentId) {
        super(String.format("Cannot delete department with ID %d as it has active patients", departmentId));
        this.departmentId = departmentId;
    }

}