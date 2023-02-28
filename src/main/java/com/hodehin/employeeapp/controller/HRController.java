package com.hodehin.employeeapp.controller;


import com.hodehin.employeeapp.dto.EmployeeDto;
import com.hodehin.employeeapp.dto.HireInfoDto;
import com.hodehin.employeeapp.model.Employee;
import com.hodehin.employeeapp.model.embedded.EmployeeDetail;
import com.hodehin.employeeapp.service.HumanResourcesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hr")
@AllArgsConstructor
public class HRController {

    private HumanResourcesService humanResourcesService;

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> hireEmployee(@PathVariable long id, @RequestBody HireInfoDto hireInfo) {
        EmployeeDto employee = humanResourcesService.hireEmployee(id, hireInfo);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/{id}/suspend")
    public ResponseEntity<EmployeeDto> suspendEmployee(@PathVariable long id) {
        EmployeeDto employee = humanResourcesService.suspendEmployee(id);
        return ResponseEntity.ok(employee);
    }
}
