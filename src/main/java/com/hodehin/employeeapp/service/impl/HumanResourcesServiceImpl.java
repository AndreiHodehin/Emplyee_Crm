package com.hodehin.employeeapp.service.impl;


import com.hodehin.employeeapp.dto.HireInfoDto;
import com.hodehin.employeeapp.enums.Departments;
import com.hodehin.employeeapp.exception.EmployeeAlreadyHiredException;
import com.hodehin.employeeapp.exception.EmployeeNotFoundException;
import com.hodehin.employeeapp.model.Department;
import com.hodehin.employeeapp.model.Employee;
import com.hodehin.employeeapp.model.embedded.EmployeeDetail;
import com.hodehin.employeeapp.repositiry.DepartmentRepository;
import com.hodehin.employeeapp.repositiry.EmployeeRepository;
import com.hodehin.employeeapp.service.HumanResourcesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class HumanResourcesServiceImpl implements HumanResourcesService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public Employee hireEmployee(long id, HireInfoDto hireInfo) {
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new EmployeeNotFoundException("Employee doesnt exists in database"));
        if(employee.getEmployeeDetail() == null ) {
            EmployeeDetail detail = new EmployeeDetail(LocalDate.now(),hireInfo.getSalary());
            employee.setEmployeeDetail(detail);
            if(hireInfo.getDepartment() != null) {
                Department department = departmentRepository.findByName(Departments.valueOf(hireInfo.getDepartment().toUpperCase())).orElseThrow();
                department.addEmployee(employee);
            }
        } else {
            throw new EmployeeAlreadyHiredException("Employee hired earlier");
        }
        employeeRepository.flush();
        return employee;
    }

    @Override
    public void suspendEmployee(long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()-> new EmployeeNotFoundException("Employee doesnt exists in database"));
        if(employee.getEmployeeDetail() != null) {
            employee.setEmployeeDetail(null);
            employee.setDepartment(null);
        }
        employeeRepository.flush();
    }


}
