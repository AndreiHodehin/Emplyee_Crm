package com.hodehin.employeeapp.repositiry;

import com.hodehin.employeeapp.enums.Departments;
import com.hodehin.employeeapp.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByName(Departments name);
}
