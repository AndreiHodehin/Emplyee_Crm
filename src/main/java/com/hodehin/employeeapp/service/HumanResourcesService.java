package com.hodehin.employeeapp.service;

import com.hodehin.employeeapp.dto.EmployeeDto;
import com.hodehin.employeeapp.dto.HireInfoDto;
import com.hodehin.employeeapp.exception.EmployeeNotFoundException;
import com.hodehin.employeeapp.model.Employee;
import com.hodehin.employeeapp.model.embedded.EmployeeDetail;

public interface HumanResourcesService {

    EmployeeDto hireEmployee(long id, HireInfoDto hireInfo) ;

    EmployeeDto suspendEmployee(long id);
}
