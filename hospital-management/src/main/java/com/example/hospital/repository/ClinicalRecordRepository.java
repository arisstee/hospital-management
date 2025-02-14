package com.example.hospital.repository;

import com.example.hospital.entity.ClinicalRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClinicalRecordRepository extends JpaRepository<ClinicalRecord, Long> {
}
