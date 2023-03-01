package com.hodehin.employeeapp.utils;

import com.hodehin.employeeapp.dto.DepartmentDto;
import com.hodehin.employeeapp.dto.EmployeeDto;
import com.hodehin.employeeapp.dto.TaskDto;
import com.hodehin.employeeapp.model.Department;
import com.hodehin.employeeapp.model.Employee;
import com.hodehin.employeeapp.model.Task;
import com.hodehin.employeeapp.model.embedded.EmployeeDetail;
import com.hodehin.employeeapp.model.embedded.EmployeePersonalInfo;
import org.springframework.stereotype.Component;



@Component
public class Converter {

    public EmployeeDto employeeToDto(Employee employee) {
        return new EmployeeDto(employee.getId()
                , employee.getFirstName()
                , employee.getLastName()
                , employee.getDepartment() == null ? null : employee.getDepartment().getName().name()
                , employee.getPersonalInfo() == null ? null : employee.getPersonalInfo().getBirthDate()
                , employee.getPersonalInfo() == null ? null : employee.getPersonalInfo().getAddress()
                , employee.getPersonalInfo() == null ? null : employee.getPersonalInfo().getPhoneNumber()
                , employee.getEmployeeDetail() == null ? null : employee.getEmployeeDetail().getHired()
                , employee.getEmployeeDetail() == null ? 0 : employee.getEmployeeDetail().getSalary()
                , employee.getTasks().stream().map(this::taskToDto).toList());
    }
    public Employee employeeToEntity(EmployeeDto employeeDTO) {
        Employee employee = new Employee();
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        EmployeePersonalInfo personalInfo = new EmployeePersonalInfo(employeeDTO.getBirthDate()
                                                                        , employeeDTO.getAddress()
                                                                        ,employeeDTO.getPhoneNumber());
        System.out.println(personalInfo);
        employee.setPersonalInfo(personalInfo);
        EmployeeDetail detail = new EmployeeDetail(employeeDTO.getHired(), employeeDTO.getSalary());
        employee.setEmployeeDetail(detail);

        return employee;
    }
    public TaskDto taskToDto(Task task) {
        return  new TaskDto(task.getId(),
                task.getDescription(),
                task.getStartTime(),
                task.getCompleted(),
                task.getEstimate(),
                task.getEmployees().stream().map(Employee::getId).toList());
    }

    public Task taskToEntity(TaskDto taskDto) {
        Task task = new Task();
        task.setDescription(taskDto.getDescription());
        task.setEstimate(taskDto.getEstimate());
        task.setStartTime(taskDto.getStartTime());
        task.setCompleted(taskDto.getCompleted());
        return task;
    }

    public DepartmentDto departmentToDto(Department department) {
        return new DepartmentDto(department.getId()
                                , department.getName()
                                , department.getLocation()
                                , department.getEmployees().stream().map(Employee::getId).toList());
    }

    public Department departmentToEntity(DepartmentDto departmentDto) {
        Department department = new Department();
        department.setLocation(departmentDto.getLocation());
        department.setName(departmentDto.getName());
        return department;
    }
}
