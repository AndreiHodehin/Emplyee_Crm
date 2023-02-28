package com.hodehin.employeeapp.service.impl;

import com.hodehin.employeeapp.dto.EmployeeDto;
import com.hodehin.employeeapp.dto.EmployeeInfoDto;
import com.hodehin.employeeapp.exception.DepartmentNotFoundException;
import com.hodehin.employeeapp.exception.EmployeeNotFoundException;
import com.hodehin.employeeapp.model.Department;
import com.hodehin.employeeapp.model.Employee;
import com.hodehin.employeeapp.model.embedded.EmployeePersonalInfo;
import com.hodehin.employeeapp.repositiry.DepartmentRepository;
import com.hodehin.employeeapp.repositiry.EmployeeRepository;
import com.hodehin.employeeapp.service.EmployeeService;
import com.hodehin.employeeapp.utils.Converter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private Converter converter;

    @Override
    public EmployeeDto getEmployee(long id) {
        Employee employee =  employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
        return converter.employeeToDto(employee);
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        return employeeRepository.findAll().stream().map(employee -> converter.employeeToDto(employee)).toList();
    }

    @Override
    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void changeInfo(EmployeeInfoDto employeeInfoDto, long id) {
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

    }

    @Override
    public void setDepartmentToEmpl(Long id, Long departmentId) {
        Employee employee = employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
        Department department = departmentRepository.findById(departmentId).orElseThrow(()->new DepartmentNotFoundException("Department not Exists"));
        department.addEmployee(employee);
        employeeRepository.flush();
    }
}
