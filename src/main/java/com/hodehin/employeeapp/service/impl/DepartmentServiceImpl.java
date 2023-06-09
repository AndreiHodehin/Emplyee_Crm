package com.hodehin.employeeapp.service.impl;


import com.hodehin.employeeapp.dto.DepartmentSimpleDto;
import com.hodehin.employeeapp.exception.DepartmentNotFoundException;
import com.hodehin.employeeapp.model.Department;
import com.hodehin.employeeapp.repositiry.DepartmentRepository;
import com.hodehin.employeeapp.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository repository;
    @Override
    public void addDepartment(Department departmentReq) {
        repository.save(departmentReq);
    }

    @Override
    public Department getDepartmentById(Long id) {
        return repository.findById(id).orElseThrow(()->new DepartmentNotFoundException("Department not Exists"));
    }

    @Override
    public List<Department> getAllDepartment() {
        return repository.findAll();
    }

    @Override
    public Department updateDepartment(Long id, Department newDepartment) {
        Department department = getDepartmentById(id);
        if(newDepartment.getName() != null){
            department.setName(newDepartment.getName());
        }
        if(newDepartment.getEmployees() != null) {
            department.getEmployees().addAll(newDepartment.getEmployees());
        }
        if(newDepartment.getLocation() != null) {
            department.setLocation(newDepartment.getLocation());
        }

        return department;
    }

    @Override
    public void deleteDepartmentById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<DepartmentSimpleDto> getAllPageable(Pageable pageable) {
        return repository.findAllBy(DepartmentSimpleDto.class, pageable);
    }


}
