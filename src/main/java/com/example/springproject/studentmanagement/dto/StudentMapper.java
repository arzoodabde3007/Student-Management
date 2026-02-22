package com.example.springproject.studentmanagement.dto;


import com.example.springproject.studentmanagement.Entities.Student;

public class StudentMapper {

    public static Student requestDTOToEntity(StudentRequestDTO studentDTO){

        Student student = new Student();
        student.setStudentName(studentDTO.getStudentName());
        student.setEmail(studentDTO.getEmail());
        student.setPassword(studentDTO.getPassword());
        student.setCourse(studentDTO.getCourse());
        student.setAddress(studentDTO.getAddress());

        return student;
    }

    public static StudentResponseDTO studentEntityToResponse(Student student){
        StudentResponseDTO responseDTO = new StudentResponseDTO();
        responseDTO.setStudentId(student.getStudentId());
        responseDTO.setStudentName(student.getStudentName());
        responseDTO.setEmail(student.getEmail());
        responseDTO.setCourse(student.getCourse());
        responseDTO.setAddress(student.getAddress());
        return responseDTO;
    }
}
