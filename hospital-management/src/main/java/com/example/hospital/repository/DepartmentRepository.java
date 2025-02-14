package com.example.hospital.repository;

import com.example.hospital.entity.Department;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Transactional
    @Modifying
    @Query(value = "SELECT d.name AS departmentName, COALESCE(p.name, '') AS patientName " +
            "FROM hospital.departments d " +
            "LEFT JOIN hospital.patients p ON d.id = p.department_id " +
            "WHERE d.name LIKE %:keyword% OR p.name LIKE %:keyword%", nativeQuery = true)
    List<Object[]> findDepartmentsWithPatients(@Param("keyword") String keyword);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "delete from hospital.departments where id= :id")
    void deleteDepartment(Long id);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "update hospital.departments set name= :name," +
            "where id= :id ")
    void update(String name);


}
