package com.hodehin.employeeapp.service;


import com.hodehin.employeeapp.dto.HireInfoDto;
import com.hodehin.employeeapp.model.Employee;


public interface HumanResourcesService {

    Employee hireEmployee(long id, HireInfoDto hireInfo) ;
    void suspendEmployee(long id);
}
