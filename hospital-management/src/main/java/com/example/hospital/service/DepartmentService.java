package com.example.hospital.service;

import com.example.hospital.entity.Department;
import com.example.hospital.exceptions.DepartmentHasActivePatientsException;
import com.example.hospital.exceptions.ResourceNotFoundException;
import com.example.hospital.repository.DepartmentRepository;
import com.example.hospital.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class DepartmentService {

    @Autowired
    DepartmentRepository repository;

    @Autowired
    private PatientRepository patientRepository;

    public List<Department> getAllDepartments (){
        return repository.findAll();
    }

    public Department getDepartmentById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department", id));
    }


    public List<Map<String, Object>> searchDepartmentsWithPatients(String keyword) {
        List<Object[]> results = repository.findDepartmentsWithPatients(keyword);

        List<Map<String, Object>> response = new ArrayList<>();
        for (Object[] row : results) {
            Map<String, Object> map = new HashMap<>();
            map.put("departmentName", row[0]);
            map.put("patientName", row[1]);
            response.add(map);
        }
        return response;
    }

    @Transactional
    public void deleteDepartment(Long departmentId) {
        Department department = repository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department", departmentId));

        if (patientRepository.existsByDepartmentId(departmentId)) {
            throw new DepartmentHasActivePatientsException(departmentId);
        }

        repository.delete(department);
    }

    @Transactional
    public void update(Department department){
        repository.update(department.getName());
    }

}
