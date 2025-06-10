package com.example.hospital.controller;

import com.example.hospital.dto.ClinicalRecordsDTO;
import com.example.hospital.entity.ClinicalRecord;
import com.example.hospital.service.ClinicalRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clinicalRecords")
public class ClinicalRecordController {

    @Autowired
    private ClinicalRecordService service;

    @GetMapping("/getAll")
    public ResponseEntity<List<ClinicalRecord>> getAll() {
        List<ClinicalRecord> records = service.getAllClinicalRecords();
        return new ResponseEntity<>(records, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ClinicalRecord> create(@RequestBody ClinicalRecordsDTO clinicalRecordsDTO) {
        ClinicalRecord record = service.createClinicalRecord(clinicalRecordsDTO);
        return new ResponseEntity<>(record, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ClinicalRecord> update(@PathVariable Long id, @RequestBody ClinicalRecordsDTO clinicalRecordsDTO) {
        ClinicalRecord record = service.updateClinicalRecord(id, clinicalRecordsDTO);
        return new ResponseEntity<>(record, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteClinicalRecord(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ClinicalRecord>> search(@RequestParam String keyword) {
        List<ClinicalRecord> records = service.searchClinicalRecords(keyword);
        return new ResponseEntity<>(records, HttpStatus.OK);
    }
}