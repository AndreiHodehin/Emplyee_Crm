package com.hodehin.employeeapp.service.impl;

import com.hodehin.employeeapp.dto.EmployeeInfoDto;
import com.hodehin.employeeapp.model.Department;
import com.hodehin.employeeapp.model.Employee;
import com.hodehin.employeeapp.model.embedded.EmployeePersonalInfo;
import com.hodehin.employeeapp.repositiry.DepartmentRepository;
import com.hodehin.employeeapp.repositiry.EmployeeRepository;
import com.hodehin.employeeapp.service.DepartmentService;
import com.hodehin.employeeapp.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private DepartmentService departmentService;

    @Override
    public Employee getEmployee(long id) {
        return employeeRepository.findById(id).orElseThrow();
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee changeInfo(EmployeeInfoDto employeeInfoDto, long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow();
        EmployeePersonalInfo personalInfo = employee.getPersonalInfo() == null ? new EmployeePersonalInfo(): employee.getPersonalInfo();
        employee.setPersonalInfo(personalInfo);

        if (employeeInfoDto.getAddress() != null) {
            personalInfo.setAddress(employeeInfoDto.getAddress());
        }
        if (employeeInfoDto.getBirthDate() != null) {
            personalInfo.setBirthDate(employeeInfoDto.getBirthDate());
        }
        if (employeeInfoDto.getPhoneNumber() != null) {
            personalInfo.setPhoneNumber(employeeInfoDto.getPhoneNumber());
        }
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public Employee setDepartmentToEmpl(Long id, Long departmentId) {
        Employee employee = getEmployee(id);
        Department department = departmentService.getDepartmentById(departmentId);
        department.addEmployee(employee);
        employeeRepository.flush();
        return employee;
    }
}
