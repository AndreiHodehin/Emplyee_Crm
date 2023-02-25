package com.hodehin.employeeapp.controller;


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
    public ResponseEntity<Employee> hireEmployee(@PathVariable long id, @RequestBody EmployeeDetail detail) {
        Employee employee = humanResourcesService.hireEmployee(id, detail);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/{id}/suspend")
    public ResponseEntity<Employee> suspendEmployee(@PathVariable long id) {
        Employee employee = humanResourcesService.suspendEmployee(id);
        return ResponseEntity.ok(employee);
    }
}
