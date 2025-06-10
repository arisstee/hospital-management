package com.example.hospital.service;

import com.example.hospital.dto.ClinicalRecordsDTO;
import com.example.hospital.entity.ClinicalRecord;
import com.example.hospital.entity.Patient;
import com.example.hospital.exceptions.ResourceNotFoundException;
import com.example.hospital.repository.ClinicalRecordRepository;
import com.example.hospital.repository.PatientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ClinicalRecordService {

    @Autowired
    private ClinicalRecordRepository repository;

    @Autowired
    private PatientRepository patientRepository;

    public List<ClinicalRecord> getAllClinicalRecords() {
        return repository.findAll();
    }

    public ClinicalRecord createClinicalRecord(ClinicalRecordsDTO clinicalRecordsDTO) {
        Patient patient = patientRepository.findById(clinicalRecordsDTO.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        ClinicalRecord clinicalRecord = new ClinicalRecord();
        clinicalRecord.setPatient(patient);
        clinicalRecord.setNotes(clinicalRecordsDTO.getNotes());

        return repository.save(clinicalRecord);
    }

    public ClinicalRecord updateClinicalRecord(Long id, ClinicalRecordsDTO clinicalRecordsDTO) {
        ClinicalRecord existingRecord = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clinical record not found"));

        if (clinicalRecordsDTO.getPatientId() != null) {
            Patient patient = patientRepository.findById(clinicalRecordsDTO.getPatientId())
                    .orElseThrow(() -> new RuntimeException("Patient not found"));
            existingRecord.setPatient(patient);
        }

        if (clinicalRecordsDTO.getNotes() != null) {
            existingRecord.setNotes(clinicalRecordsDTO.getNotes());
        }

        return repository.save(existingRecord);
    }

    public void deleteClinicalRecord(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Clinical record not found");
        }
        repository.deleteById(id);
    }

    public List<ClinicalRecord> searchClinicalRecords(String keyword) {
        return repository.findByNotesContainingIgnoreCase(keyword);
    }


}