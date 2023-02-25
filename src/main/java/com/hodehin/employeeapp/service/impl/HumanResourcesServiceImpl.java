package com.hodehin.employeeapp.service.impl;


import com.hodehin.employeeapp.exception.EmployeeNotFoundException;
import com.hodehin.employeeapp.model.Employee;
import com.hodehin.employeeapp.model.embedded.EmployeeDetail;
import com.hodehin.employeeapp.repositiry.EmployeeRepository;
import com.hodehin.employeeapp.service.HumanResourcesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class HumanResourcesServiceImpl implements HumanResourcesService {

    private EmployeeRepository employeeRepository;

    @Override
    public Employee hireEmployee(long id, EmployeeDetail detail) {
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new EmployeeNotFoundException("Employee doesnt exists in database"));
        if(employee.getEmployeeDetail() == null ) {
            detail.setHired(LocalDate.now());
            employee.setEmployeeDetail(detail);
        } else {
            double currentSalary = employee.getEmployeeDetail().getSalary();
            double newSalary = detail.getSalary();
            employee.getEmployeeDetail().setSalary(newSalary!=0 ? newSalary: currentSalary);
        }
        employeeRepository.flush();
        return employee;
    }

    @Override
    public Employee suspendEmployee(long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(()-> new EmployeeNotFoundException("Employee doesnt exists in database"));
        if(employee.getEmployeeDetail() != null) {
            employee.setEmployeeDetail(null);
            employee.setDepartment(null);
        }
        employeeRepository.flush();
        return employee;
    }


}
