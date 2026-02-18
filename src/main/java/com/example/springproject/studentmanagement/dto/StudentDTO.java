package com.example.springproject.studentmanagement.dto;

import com.example.springproject.studentmanagement.Entities.Student;
import lombok.Data;

@Data
public class StudentDTO {

    private String studentName;
    private String email;
    private String course;

    public StudentDTO studentMapper(Student student) {
        this.setStudentName(student.getStudentName());
        this.setEmail(student.getEmail());
        this.setCourse(student.getCourse());
        return this;
    }
}
