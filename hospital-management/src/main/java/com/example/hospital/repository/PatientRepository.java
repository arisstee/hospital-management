package com.example.hospital.repository;

import com.example.hospital.entity.Department;
import com.example.hospital.entity.Patient;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {


    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE hospital.patients SET " +
            "first_name = :firstName, " +
            "last_name = :lastName, " +
            "birth_date = :birthDate, " +
            "department_id = :departmentId, " +
            "admission_date = :admissionDate, " +
            "discharge_date = :dischargeDate, " +
            "discharge_reason = :dischargeReason, " +
            "status = :status " +
            "WHERE id = :id")
    void updatePatient(
            @Param("firstName") String firstName,
            @Param("lastName") String lastName,
            @Param("birthDate") LocalDate birthDate,
            @Param("departmentId") Long departmentId,
            @Param("admissionDate") LocalDate admissionDate,
            @Param("dischargeDate") LocalDate dischargeDate,
            @Param("dischargeReason") String dischargeReason,
            @Param("status") String status,
            @Param("id") Long id
    );


        List<Patient> findByFirstNameContainingIgnoreCase(String firstName);
    @Query("SELECT COUNT(p) > 0 FROM Patient p WHERE p.department.id = :departmentId")
    boolean existsByDepartmentId(@Param("departmentId") Long departmentId);
}


