package com.example.hospital.controller;
import com.example.hospital.entity.ClinicalRecord;
import com.example.hospital.service.ClinicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clinicalRecords")
public class ClinicalRecordController {

    @Autowired
    ClinicalRecordService service;

    @GetMapping("/getAll")
    public ResponseEntity<List<ClinicalRecord>> getAll(){
        List<ClinicalRecord> patients=service.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }
}
