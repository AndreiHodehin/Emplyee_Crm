package com.hodehin.employeeapp.service;


import com.hodehin.employeeapp.dto.DepartmentSimpleDto;
import com.hodehin.employeeapp.model.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DepartmentService {
    void addDepartment(Department departmentReq);

    Department getDepartmentById(Long id);

    List<Department> getAllDepartment();

    Department updateDepartment(Long id, Department department);

    void deleteDepartmentById(Long id);

    Page<DepartmentSimpleDto> getAllPageable(Pageable pageable);
}
