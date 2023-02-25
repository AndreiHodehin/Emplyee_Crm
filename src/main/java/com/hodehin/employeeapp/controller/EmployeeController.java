package com.hodehin.employeeapp.controller;

import com.hodehin.employeeapp.dto.EmployeeDTO;
import com.hodehin.employeeapp.dto.EmployeeInfoDto;
import com.hodehin.employeeapp.model.Department;
import com.hodehin.employeeapp.model.Employee;
import com.hodehin.employeeapp.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable long id) {
        Employee employee = employeeService.getEmployee(id);
        return ResponseEntity.ok(employee);
    }

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
        return ResponseEntity.ok(employee);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployee() {
        List<Employee> employees = employeeService.getAllEmployee();
        return ResponseEntity.ok(employees);
    }
    @PostMapping("/{id}")
    public ResponseEntity<Employee> changeInfo(@PathVariable long id, @RequestBody EmployeeInfoDto employeeInfoDto) {
        Employee employee = employeeService.changeInfo(employeeInfoDto, id);
        return ResponseEntity.ok(employee);
    }
    @PostMapping("/{id}/department/{departmentId}")
    public ResponseEntity<Employee> setDepartment(@PathVariable Long id, @PathVariable Long departmentId) {
        Employee employee = employeeService.setDepartmentToEmpl(id,departmentId);
        return ResponseEntity.ok(employee);
    }

}
