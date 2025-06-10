package com.example.hospital.controller;

import com.example.hospital.dto.PatientUpdateDTO;
import com.example.hospital.entity.Patient;
import com.example.hospital.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")

public class PatientController {
    @Autowired
    PatientService service;

    @GetMapping("/getAll")
    public ResponseEntity<List<Patient>> getAll(){
        List<Patient> patients=service.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<PatientUpdateDTO> updatePatient(@RequestBody PatientUpdateDTO patientDTO) {
        service.updatePatient(patientDTO);
        return ResponseEntity.ok(patientDTO);
    }
    @PostMapping("/create")
    public ResponseEntity<Patient> createPatient(@RequestBody PatientUpdateDTO patientDTO) {
        Patient createdPatient = service.createPatient(patientDTO);
        return new ResponseEntity<>(createdPatient, HttpStatus.CREATED); // Returning 201 Created with the patient object
    }

    @GetMapping("/search")
    public ResponseEntity<List<Patient>> searchPatient(@RequestParam String firstName) {
        return ResponseEntity.ok(service.searchPatient(firstName));
    }


}