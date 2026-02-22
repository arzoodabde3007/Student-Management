package com.example.springproject.studentmanagement.dto;

import com.example.springproject.studentmanagement.Entities.Address;
import com.example.springproject.studentmanagement.Entities.Course;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class StudentResponseDTO {

    private Long studentId;
    private String studentName;
    private String email;
    private Course course;
    private Address address;


}
