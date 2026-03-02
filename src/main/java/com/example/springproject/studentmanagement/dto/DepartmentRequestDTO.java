package com.example.springproject.studentmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DepartmentRequestDTO {

    @NotBlank(message = "Department name required")
    private String departmentName;

}
