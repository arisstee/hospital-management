package com.example.hospital.controller;

import com.example.hospital.entity.Department;
import com.example.hospital.exceptions.DepartmentHasActivePatientsException;
import com.example.hospital.exceptions.ResourceNotFoundException;
import com.example.hospital.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    DepartmentService service;

    @GetMapping("/getAll")
    public ResponseEntity<List<Department>> getAll(){
        List<Department> departments = service.getAllDepartments();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @GetMapping("/search")
    public List<Map<String, Object>> searchDepartments(@RequestParam String keyword) {
        return service.searchDepartmentsWithPatients(keyword);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody Department department){
        service.deleteDepartment(department.getId());
        return new ResponseEntity<>("Department Deleted successfully!!", HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@RequestBody Department department){
        service.update(department);
        return new ResponseEntity<>("Department Updated Succesfully!! ",HttpStatus.OK);
    }
}
