package com.example.springproject.studentmanagement.service;

import com.example.springproject.studentmanagement.Entities.Department;
import com.example.springproject.studentmanagement.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
    }

    public Department createDepartment(Department department){
        return departmentRepository.save(department);
    }

    public List<Department> getAllDepartment(){
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Long department_id){
        return departmentRepository.findById(department_id)
                .orElseThrow(
                        () -> new RuntimeException("Department not found")
                );
    }

    public String deleteDepartmentById(Long department_id){
        departmentRepository.deleteById(department_id);
        return "Department Removed";
    }
}
