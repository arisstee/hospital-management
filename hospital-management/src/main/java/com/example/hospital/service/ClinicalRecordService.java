package com.example.hospital.service;

import com.example.hospital.entity.ClinicalRecord;
import com.example.hospital.entity.Patient;
import com.example.hospital.repository.ClinicalRecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ClinicalRecordService {

    @Autowired
    ClinicalRecordRepository repository;

    public List<ClinicalRecord> getAllPatients() {
        return repository.findAll();
    }
}
