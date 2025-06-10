package com.example.hospital.repository;

import com.example.hospital.entity.ClinicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClinicalRecordRepository extends JpaRepository<ClinicalRecord, Long> {
    List<ClinicalRecord> findByNotesContainingIgnoreCase(String keyword);
    @Query("SELECT cr FROM ClinicalRecord cr JOIN FETCH cr.patient WHERE cr.patient.id = :patientId")
    List<ClinicalRecord> findByPatient_IdWithPatient(@Param("patientId") Long patientId);
}