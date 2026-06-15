package com.example.springproject.studentmanagement.service;

import com.example.springproject.studentmanagement.Entities.Department;
import com.example.springproject.studentmanagement.mappers.DepartmentMapper;
import com.example.springproject.studentmanagement.dto.DepartmentResponseDTO;
import com.example.springproject.studentmanagement.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    public DepartmentService(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper){
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    public DepartmentResponseDTO createDepartment(Department department){
        Department savedDepartment =  departmentRepository.save(department);
        return departmentMapper.entityToResponseDTO(savedDepartment);

    }

    public List<DepartmentResponseDTO> getAllDepartment(){
        List<Department> departments =  departmentRepository.findAll();
        List<DepartmentResponseDTO> departmentResponseDTOS = departments.stream()
                .map(departmentMapper::entityToResponseDTO)
                .toList();

        return departmentResponseDTOS;
    }

    public DepartmentResponseDTO getDepartmentById(Long department_id){
        Department department =  departmentRepository.findById(department_id)
                .orElseThrow(
                        () -> new RuntimeException("Department not found")
                );

        DepartmentResponseDTO responseDTO = new DepartmentResponseDTO();
        responseDTO.setDepartmentName(department.getDepartmentName());
        return responseDTO;
    }

    public String deleteDepartmentById(Long department_id){
        departmentRepository.deleteById(department_id);
        return "Department Removed";
    }
}
