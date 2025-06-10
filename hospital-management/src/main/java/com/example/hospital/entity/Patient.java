package com.example.hospital.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@SuppressWarnings("ALL")
@Entity
@Table(name = "patients")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Patient  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "first_name")
    private String firstName;

    @Column(nullable = false,name = "last_name")
    private String lastName;

    @Column(nullable = false,name = "birth_date")
    private LocalDate birthDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    @JsonBackReference
    private Department department;

    @Column(nullable = false,name = "admission_date")
    private LocalDate admissionDate;
    @Column(name = "discharge_date")
    private LocalDate dischargeDate;

    @Column(name = "discharge_reason")
    private String dischargeReason;

    @Column(name = "status")
    private String status;

}
