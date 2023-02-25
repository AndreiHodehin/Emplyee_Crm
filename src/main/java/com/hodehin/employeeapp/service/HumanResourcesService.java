package com.hodehin.employeeapp.service;

import com.hodehin.employeeapp.exception.EmployeeNotFoundException;
import com.hodehin.employeeapp.model.Employee;
import com.hodehin.employeeapp.model.embedded.EmployeeDetail;

public interface HumanResourcesService {

    Employee hireEmployee(long id, EmployeeDetail detail) ;

    Employee suspendEmployee(long id);
}
