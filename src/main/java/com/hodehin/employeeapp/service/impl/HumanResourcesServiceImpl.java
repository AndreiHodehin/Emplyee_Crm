package com.hodehin.employeeapp.service.impl;


import com.hodehin.employeeapp.dto.EmployeeDto;
import com.hodehin.employeeapp.dto.HireInfoDto;
import com.hodehin.employeeapp.enums.Departments;
import com.hodehin.employeeapp.exception.EmployeeNotFoundException;
import com.hodehin.employeeapp.model.Department;
import com.hodehin.employeeapp.model.Employee;
import com.hodehin.employeeapp.model.embedded.EmployeeDetail;
import com.hodehin.employeeapp.repositiry.DepartmentRepository;
import com.hodehin.employeeapp.repositiry.EmployeeRepository;
import com.hodehin.employeeapp.service.HumanResourcesService;
import com.hodehin.employeeapp.utils.Converter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class HumanResourcesServiceImpl implements HumanResourcesService {

    private final EmployeeRepository employeeRepository;
    private final Converter converter;
    private final DepartmentRepository departmentRepository;

    @Override
    public EmployeeDto hireEmployee(long id, HireInfoDto hireInfo) {
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new EmployeeNotFoundException("Employee doesnt exists in database"));
        if(employee.getEmployeeDetail() == null ) {
            EmployeeDetail detail = new EmployeeDetail(LocalDate.now(),hireInfo.getSalary());
            employee.setEmployeeDetail(detail);
        } else {
            if(employee.getEmployeeDetail().getHired() == null) {
                employee.getEmployeeDetail().setHired(LocalDate.now());
            }
            double currentSalary = employee.getEmployeeDetail().getSalary();
            double newSalary = hireInfo.getSalary();
            employee.getEmployeeDetail().setSalary(newSalary != 0 ? newSalary: currentSalary);
        }
        Department department = departmentRepository.findByName(Departments.valueOf(hireInfo.getDepartment().toUpperCase()));
        department.addEmployee(employee);
        employeeRepository.flush();
        return converter.employeeToDto(employee);
    }

    @Override
    public EmployeeDto suspendEmployee(long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new EmployeeNotFoundException("Employee doesnt exists in database"));
        if(employee.getEmployeeDetail() != null) {
            employee.setEmployeeDetail(null);
            employee.setDepartment(null);
        }
        employeeRepository.flush();
        return converter.employeeToDto(employee);
    }


}
