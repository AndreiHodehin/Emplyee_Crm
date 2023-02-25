package com.hodehin.employeeapp.utils;

import com.hodehin.employeeapp.dto.EmployeeDTO;
import com.hodehin.employeeapp.dto.TaskDto;
import com.hodehin.employeeapp.model.Employee;
import com.hodehin.employeeapp.model.Task;
import com.hodehin.employeeapp.model.embedded.EmployeeDetail;
import com.hodehin.employeeapp.model.embedded.EmployeePersonalInfo;
import com.hodehin.employeeapp.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class Converter {
    private final  EmployeeService employeeService;

    public EmployeeDTO employeeToDto(Employee employee) {
        return new EmployeeDTO(employee.getId()
                , employee.getFirstName()
                , employee.getLastName()
                , employee.getPersonalInfo().getBirthDate()
                , employee.getPersonalInfo().getAddress()
                , employee.getPersonalInfo().getPhoneNumber()
                , employee.getEmployeeDetail().getHired()
                , employee.getEmployeeDetail().getSalary()
                , employee.getTasks().stream().map(this::taskToDto).toList());
    }
    public Employee employeeToEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employee.getLastName());
        EmployeePersonalInfo personalInfo = new EmployeePersonalInfo(employeeDTO.getBirthDate()
                                                                        ,employee.getPersonalInfo().getAddress()
                                                                        ,employee.getPersonalInfo().getPhoneNumber());
        employee.setPersonalInfo(personalInfo);
        EmployeeDetail detail = new EmployeeDetail(employeeDTO.getHired(), employeeDTO.getSalary());
        employee.setEmployeeDetail(detail);

        employee.getTasks().addAll(employeeDTO.getTasks().stream().map(this::taskToEntity).toList());
        System.out.println(employee);
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
        List<Employee> employees = taskDto.getEmployees().stream().map(employeeService::getEmployee).toList();
        employees.forEach(task::addEmployee);
        System.out.println(task);
        return task;
    }
}
