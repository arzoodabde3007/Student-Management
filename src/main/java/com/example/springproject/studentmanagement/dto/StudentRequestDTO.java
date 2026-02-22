package com.example.springproject.studentmanagement.dto;

import com.example.springproject.studentmanagement.Entities.Address;
import com.example.springproject.studentmanagement.Entities.Course;
import lombok.Data;

@Data
public class StudentRequestDTO {
    private String studentName;
    private String email;
    private String password;
    private Course course;
    private Address address;

}
