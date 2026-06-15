package com.example.springproject.studentmanagement.mappers;

import com.example.springproject.studentmanagement.Entities.Department;
import com.example.springproject.studentmanagement.dto.DepartmentRequestDTO;
import com.example.springproject.studentmanagement.dto.DepartmentResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {

    public Department requestDTOToEntity(DepartmentRequestDTO departmentRequestDTO){
        Department department = new Department();

        department.setDepartmentName(departmentRequestDTO.getDepartmentName());
        return department;
    }

    public DepartmentResponseDTO entityToResponseDTO(Department department){
        DepartmentResponseDTO responseDTO = new DepartmentResponseDTO();

        responseDTO.setDepartmentName(department.getDepartmentName());
        return responseDTO;
    }
}
