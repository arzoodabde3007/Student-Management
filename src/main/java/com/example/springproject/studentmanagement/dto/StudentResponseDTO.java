package com.example.springproject.studentmanagement.dto;

import com.example.springproject.studentmanagement.Entities.Student;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class StudentResponseDTO {

    private Long studentId;
    private String studentName;
    private String email;
    private String course;


    public StudentResponseDTO studentMapper(Student student) {
        this.setStudentId(student.getStudentId());
        this.setStudentName(student.getStudentName());
        this.setEmail(student.getEmail());
        this.setCourse(student.getCourse());
        return this;
    }
}
