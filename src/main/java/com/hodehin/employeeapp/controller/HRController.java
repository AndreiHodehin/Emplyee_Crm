package com.hodehin.employeeapp.controller;


import com.hodehin.employeeapp.dto.EmployeeDto;
import com.hodehin.employeeapp.dto.HireInfoDto;
import com.hodehin.employeeapp.model.Employee;
import com.hodehin.employeeapp.service.HumanResourcesService;
import com.hodehin.employeeapp.utils.Converter;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hr")
@AllArgsConstructor
public class HRController {

    private final HumanResourcesService humanResourcesService;
    private final Converter converter;

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> hireEmployee(@PathVariable long id, @RequestBody HireInfoDto hireInfo) {
        Employee employee = humanResourcesService.hireEmployee(id, hireInfo);
        return ResponseEntity.ok(converter.employeeToDto(employee));
    }

    @PutMapping("/{id}/suspend")
    public ResponseEntity<EmployeeDto> suspendEmployee(@PathVariable long id) {
        Employee employee = humanResourcesService.suspendEmployee(id);
        return ResponseEntity.ok(converter.employeeToDto(employee));
    }
}
