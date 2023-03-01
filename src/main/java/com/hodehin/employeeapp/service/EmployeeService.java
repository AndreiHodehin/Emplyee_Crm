package com.hodehin.employeeapp.service;


import com.hodehin.employeeapp.dto.EmployeeDto;
import com.hodehin.employeeapp.dto.EmployeeInfoDto;
import com.hodehin.employeeapp.model.Employee;

import java.util.List;

public interface EmployeeService {
    EmployeeDto getEmployee(long id);
    void addEmployee(Employee employee);
    List<EmployeeDto> getAllEmployee();
    void deleteEmployee(long id);

    void changeInfo(EmployeeInfoDto employeeInfoDto, long id);

    void setDepartmentToEmpl(Long id, Long departmentId);

    boolean checkInEmployeeById(Long id);

    boolean checkOutEmployeeById(Long id);
}
