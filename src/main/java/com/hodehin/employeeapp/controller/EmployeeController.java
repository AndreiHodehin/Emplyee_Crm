package com.hodehin.employeeapp.controller;

import com.hodehin.employeeapp.dto.EmployeeCheckListDto;
import com.hodehin.employeeapp.dto.EmployeeDto;
import com.hodehin.employeeapp.dto.EmployeeInfoDto;
import com.hodehin.employeeapp.dto.EmployeeSimpleDto;
import com.hodehin.employeeapp.model.Employee;
import com.hodehin.employeeapp.service.EmployeeService;
import com.hodehin.employeeapp.utils.Converter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;
    private final Converter converter;
    @GetMapping(value = "/{id}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable long id) {
        Employee employee = employeeService.getEmployee(id);
        return ResponseEntity.ok(converter.employeeToDto(employee));
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee employee = converter.employeeToEntity(employeeDto);
        employeeService.addEmployee(employee);
        employeeDto.setId(employee.getId());
        return ResponseEntity.ok(employeeDto);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted");
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmployee() {
        List<Employee> employees = employeeService.getAllEmployee();
        return ResponseEntity.ok(employees.stream().map(converter::employeeToDto).toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateInfo(@PathVariable long id, @RequestBody EmployeeInfoDto employeeInfoDto) {
        employeeService.changeInfo(employeeInfoDto, id);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{id}/department/{departmentId}")
    public ResponseEntity<Object> changeDepartment(@PathVariable Long id, @PathVariable Long departmentId) {
        employeeService.setDepartmentToEmpl(id,departmentId);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{id}/checkIn")
    public ResponseEntity<Boolean> checkIn(@PathVariable Long id) {
        boolean accept = employeeService.checkInEmployeeById(id);
        return ResponseEntity.ok(accept);
    }

    @PutMapping("/{id}/checkOut")
    public ResponseEntity<Boolean> checkOut(@PathVariable Long id) {
        boolean accept = employeeService.checkOutEmployeeById(id);
        return ResponseEntity.ok(accept);
    }


    @GetMapping ("/view")
    public ResponseEntity<Page<EmployeeSimpleDto>> getAllEmployeeWithOrderAndPage(Pageable pageable) {
        Page<EmployeeSimpleDto> employees = employeeService.getAllEmployeePageable(pageable);
        return ResponseEntity.ok(employees);
    }


    @GetMapping("/checkList")
    public ResponseEntity<Page<EmployeeCheckListDto>> getAllEmployeesCheckList(Pageable pageable) {
        Page<EmployeeCheckListDto> employees = employeeService.getEmployeeCheclListInfo(pageable);
        return ResponseEntity.ok(employees);
    }
}
