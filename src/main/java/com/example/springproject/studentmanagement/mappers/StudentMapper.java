package com.example.springproject.studentmanagement.mappers;


import com.example.springproject.studentmanagement.Entities.Student;
import com.example.springproject.studentmanagement.dto.StudentRequestDTO;
import com.example.springproject.studentmanagement.dto.StudentResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {


    private final AddressMapper addressMapper;

    StudentMapper(AddressMapper addressMapper){
        this.addressMapper = addressMapper;
    }

    public Student requestDTOToEntity(StudentRequestDTO studentRequestDTO){

        Student student = new Student();
        student.setStudentName(studentRequestDTO.getStudentName());
        student.setEmail(studentRequestDTO.getEmail());
        student.setPassword(studentRequestDTO.getPassword());
        return student;
    }

    public StudentResponseDTO studentEntityToResponse(Student student){
        StudentResponseDTO responseDTO = new StudentResponseDTO();
        responseDTO.setStudentId(student.getStudentId());
        responseDTO.setStudentName(student.getStudentName());
        responseDTO.setEmail(student.getEmail());
        responseDTO.setCourse(student.getCourse());
        responseDTO.setAddress(
                addressMapper.entityToAddressResponseDTO(student.getAddress())
        );
        return responseDTO;
    }

}
