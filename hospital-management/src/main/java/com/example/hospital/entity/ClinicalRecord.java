package com.example.hospital.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "clinical_records")
@Data
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ClinicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    @JsonIgnoreProperties("clinicalRecords")
    @JsonProperty("patient")  // Explicitly enable serialization
    private Patient patient;

    @Column
    private String notes;
}
