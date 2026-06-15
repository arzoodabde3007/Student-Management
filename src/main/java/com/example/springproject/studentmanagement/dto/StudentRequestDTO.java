package com.example.springproject.studentmanagement.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class StudentRequestDTO {

    @NotBlank(message = "Student Name should not be empty")
    private String studentName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email should not be empty")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password should be 8 character long")
    private String password;

    @NotNull(message = "Course ID is required")
    @Min(1)
    private Long courseId;

    @NotNull(message = "Address ID is required")
    @Min(1)
    private Long addressId;
}
