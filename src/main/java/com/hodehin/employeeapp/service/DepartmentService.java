package com.hodehin.employeeapp.service;

import com.hodehin.employeeapp.model.Department;

import java.util.List;

public interface DepartmentService {
    Department addDepartment(Department departmentReq);

    Department getDepartmentById(Long id);

    List<Department> getAllDepartment();

    Department updateDepartment(Long id, Department department);

    void deleteDepartmentById(Long id);
}
