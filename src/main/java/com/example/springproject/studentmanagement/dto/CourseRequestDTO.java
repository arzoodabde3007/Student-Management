package com.example.springproject.studentmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CourseRequestDTO {

    @NotBlank(message = "Course Name required")
    private String courseName;

    @NotBlank(message = "HOD name required")
    private String courseHOD;

    @NotNull(message = "Course should associate with department")
    private Long departmentId;

}
