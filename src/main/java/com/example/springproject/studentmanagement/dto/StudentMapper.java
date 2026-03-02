package com.example.springproject.studentmanagement.dto;


import com.example.springproject.studentmanagement.Entities.Address;
import com.example.springproject.studentmanagement.Entities.Student;

public class StudentMapper {

    public static Student requestDTOToEntity(StudentRequestDTO studentRequestDTO){

        Student student = new Student();
        student.setStudentName(studentRequestDTO.getStudentName());
        student.setEmail(studentRequestDTO.getEmail());
        student.setPassword(studentRequestDTO.getPassword());
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
