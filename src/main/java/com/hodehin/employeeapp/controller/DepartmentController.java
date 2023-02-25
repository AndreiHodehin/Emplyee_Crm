package com.hodehin.employeeapp.controller;

import com.hodehin.employeeapp.model.Department;
import com.hodehin.employeeapp.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/department")
@AllArgsConstructor
public class DepartmentController {

    private DepartmentService service;

    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody Department departmentReq) {
        Department department = service.addDepartment(departmentReq);
        return ResponseEntity.ok(department);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        Department department = service.getDepartmentById(id);
        return ResponseEntity.ok(department);
    }
    @GetMapping()
    public ResponseEntity<List<Department>> getAllDepartment() {
        List<Department> departments = service.getAllDepartment();
        return ResponseEntity.ok(departments);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@RequestBody Department department,
                                                       @PathVariable Long id){
        Department updated = service.updateDepartment(id,department);
        return ResponseEntity.ok(updated);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteDepartment(@PathVariable Long id) {
        service.deleteDepartmentById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
