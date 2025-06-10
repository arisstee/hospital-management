package com.example.hospital.service;
import com.example.hospital.dto.ClinicalRecordsDTO;
import com.example.hospital.dto.PatientUpdateDTO;
import com.example.hospital.entity.ClinicalRecord;
import com.example.hospital.entity.Department;
import com.example.hospital.entity.Patient;
import com.example.hospital.exceptions.ResourceNotFoundException;
import com.example.hospital.repository.ClinicalRecordRepository;
import com.example.hospital.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class PatientService {
    @Autowired
    PatientRepository repository;

    @Autowired
    ClinicalRecordRepository clinicalRecordRepository;

    @Autowired
    private PatientRepository patientRepository;

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient", id));
    }

    public List<Patient> getAllPatients() {
        return repository.findAll();
    }
@Transactional
    public void updatePatient(PatientUpdateDTO patientDTO) {
        repository.updatePatient(
                patientDTO.getFirstName(),
                patientDTO.getLastName(),
                patientDTO.getBirthDate(),
                patientDTO.getDepartmentId(),
                patientDTO.getAdmissionDate(),
                patientDTO.getDischargeDate(),
                patientDTO.getDischargeReason(),
                patientDTO.getStatus(),
                patientDTO.getId()
        );
    }

    @Transactional
    public Patient createPatient(PatientUpdateDTO patientDTO) {
        // Fetch the department from the database
        Department department = repository.findById(patientDTO.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found")).getDepartment();

        // Convert DTO to entity
        Patient patient = new Patient();
        patient.setFirstName(patientDTO.getFirstName());
        patient.setLastName(patientDTO.getLastName());
        patient.setBirthDate(patientDTO.getBirthDate());
        patient.setAdmissionDate(patientDTO.getAdmissionDate());
        patient.setDischargeDate(patientDTO.getDischargeDate());
        patient.setDischargeReason(patientDTO.getDischargeReason());
        patient.setStatus(patientDTO.getStatus());

        // ✅ Set the correct department
        patient.setDepartment(department);

        return repository.save(patient); // Save and return the saved patient
    }
    public List<Patient> searchPatient(String firstName) {
        return repository.findByFirstNameContainingIgnoreCase(firstName);
    }

    @Transactional
    public void deletePatient(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Patient", id);
        }
        repository.deleteById(id);
    }

    public ClinicalRecord addClinicalRecord(ClinicalRecordsDTO clinicalRecordDTO) {
        Patient patient = repository.findById(clinicalRecordDTO.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient", clinicalRecordDTO.getPatientId()));

        ClinicalRecord record = new ClinicalRecord();
        record.setPatient(patient);
        record.setNotes(clinicalRecordDTO.getNotes());

        return clinicalRecordRepository.save(record);
    }

    public List<ClinicalRecord> getClinicalRecordsByPatientId(Long patientId) {
        if (!repository.existsById(patientId)) {
            throw new ResourceNotFoundException("Patient", patientId);
        }
        return clinicalRecordRepository.findByPatient_IdWithPatient(patientId);
    }
}

