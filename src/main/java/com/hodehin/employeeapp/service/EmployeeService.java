package com.hodehin.employeeapp.service;


import com.hodehin.employeeapp.dto.EmployeeInfoDto;
import com.hodehin.employeeapp.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee getEmployee(long id);
    void addEmployee(Employee employee);
    List<Employee> getAllEmployee();
    void deleteEmployee(long id);

    Employee changeInfo(EmployeeInfoDto employeeInfoDto, long id);

    Employee setDepartmentToEmpl(Long id, Long departmentId);
}
