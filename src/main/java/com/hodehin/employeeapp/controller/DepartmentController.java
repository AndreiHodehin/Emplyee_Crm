package com.hodehin.employeeapp.controller;

import com.hodehin.employeeapp.dto.DepartmentDto;
import com.hodehin.employeeapp.model.Department;
import com.hodehin.employeeapp.service.DepartmentService;
import com.hodehin.employeeapp.utils.Converter;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/department")
@AllArgsConstructor
public class DepartmentController {

    private final DepartmentService service;
    private final Converter converter;

    @PostMapping
    public ResponseEntity<Object> createDepartment(@RequestBody DepartmentDto departmentDto) {
         Department departmentReq = converter.departmentToEntity(departmentDto);
         service.addDepartment(departmentReq);
        return ResponseEntity.ok(null);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable Long id) {
        Department department = service.getDepartmentById(id);
        DepartmentDto dto = converter.departmentToDto(department);
        return ResponseEntity.ok(dto);
    }
    @GetMapping()
    public ResponseEntity<List<DepartmentDto>> getAllDepartment() {
        List<Department> departments = service.getAllDepartment();
        List<DepartmentDto> dtos = departments.stream().map(converter::departmentToDto).toList();
        return ResponseEntity.ok(dtos);
    }
    @PutMapping("/{id}")
    public ResponseEntity<DepartmentDto> updateDepartment(@RequestBody DepartmentDto departmentDto,
                                                       @PathVariable Long id){
        Department department = converter.departmentToEntity(departmentDto);
        Department updated = service.updateDepartment(id,department);
        DepartmentDto dto = converter.departmentToDto(updated);
        return ResponseEntity.ok(dto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id) {
        service.deleteDepartmentById(id);
        return ResponseEntity.ok("Deleted successful");
    }
}
