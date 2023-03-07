package com.hodehin.employeeapp.service.impl;

import com.hodehin.employeeapp.dto.EmployeeCheckListDto;
import com.hodehin.employeeapp.dto.EmployeeInfoDto;
import com.hodehin.employeeapp.dto.EmployeeSimpleDto;
import com.hodehin.employeeapp.exception.DepartmentNotFoundException;
import com.hodehin.employeeapp.exception.EmployeeNotFoundException;
import com.hodehin.employeeapp.model.ClockInOutEmployee;
import com.hodehin.employeeapp.model.Department;
import com.hodehin.employeeapp.model.Employee;
import com.hodehin.employeeapp.model.embedded.EmployeePersonalInfo;
import com.hodehin.employeeapp.repositiry.CheckInRepository;
import com.hodehin.employeeapp.repositiry.DepartmentRepository;
import com.hodehin.employeeapp.repositiry.EmployeeRepository;
import com.hodehin.employeeapp.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;
    private DepartmentRepository departmentRepository;
    private CheckInRepository checkInRepository;

    @Override
    public Employee getEmployee(long id) {
        return employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
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

    @Override
    public boolean checkInEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
        if(employee.canCheckIn()) {
            ClockInOutEmployee checkIn = new ClockInOutEmployee();
            checkIn.setEmployee(employee);
            checkIn.setTimeIn(LocalDateTime.now());
            employee.getCheckList().add(checkIn);
            checkInRepository.save(checkIn);
            return true;
        }
        return false;
    }

    @Override
    public boolean checkOutEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(EmployeeNotFoundException::new);
        List<ClockInOutEmployee> list = employee.getCheckList();
        if(employee.canCheckOut()) {
            ClockInOutEmployee lastRecord = list.get(list.size()-1);
            lastRecord.setTimeOut(LocalDateTime.now());
            checkInRepository.flush();
            return true;
        }
        return false;
    }

    @Override
    public Page<EmployeeSimpleDto> getAllEmployeePageable(Pageable pageable) {
        return employeeRepository.findAllBy(EmployeeSimpleDto.class,pageable);

    }

    @Override
    public Page<EmployeeCheckListDto> getEmployeeCheclListInfo(Pageable pageable) {
        return employeeRepository.findAllCheckListInfo(pageable);
    }


}
