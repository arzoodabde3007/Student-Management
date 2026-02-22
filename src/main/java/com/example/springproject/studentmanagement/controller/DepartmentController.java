package com.example.springproject.studentmanagement.controller;

import com.example.springproject.studentmanagement.Entities.Department;
import com.example.springproject.studentmanagement.service.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<Department>> getDepartment(){
        return ResponseEntity.ok(departmentService.getAllDepartment());
    }

    @GetMapping("/{departmentId}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long departmentId){
        return ResponseEntity.ok(departmentService.getDepartmentById(departmentId));
    }

    @PostMapping
    public ResponseEntity<Department> createDepartment(@RequestBody Department department){
        return ResponseEntity.ok(departmentService.createDepartment(department));
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long departmentId){
        return ResponseEntity.ok(departmentService.deleteDepartmentById(departmentId));
    }
}
