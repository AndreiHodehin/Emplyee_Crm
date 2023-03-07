package com.hodehin.employeeapp.service;


import com.hodehin.employeeapp.dto.EmployeeCheckListDto;
import com.hodehin.employeeapp.dto.EmployeeInfoDto;
import com.hodehin.employeeapp.dto.EmployeeSimpleDto;
import com.hodehin.employeeapp.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    Employee getEmployee(long id);
    void addEmployee(Employee employee);
    List<Employee> getAllEmployee();
    void deleteEmployee(long id);

    void changeInfo(EmployeeInfoDto employeeInfoDto, long id);

    void setDepartmentToEmpl(Long id, Long departmentId);

    boolean checkInEmployeeById(Long id);

    boolean checkOutEmployeeById(Long id);

    Page<EmployeeSimpleDto> getAllEmployeePageable(Pageable pageable);

    Page<EmployeeCheckListDto> getEmployeeCheclListInfo(Pageable pageable);
}
