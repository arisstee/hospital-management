package com.example.hospital.dto;

import com.example.hospital.entity.Patient;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ClinicalRecordsDTO {

    private Long id;
    private Long patientId;
    private Patient patient;
    private String notes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

