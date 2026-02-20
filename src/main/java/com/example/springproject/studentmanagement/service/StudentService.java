package com.example.springproject.studentmanagement.service;

import com.example.springproject.studentmanagement.Entities.Student;
import com.example.springproject.studentmanagement.dto.StudentMapper;
import com.example.springproject.studentmanagement.dto.StudentRequestDTO;
import com.example.springproject.studentmanagement.dto.StudentResponseDTO;
import com.example.springproject.studentmanagement.exceptions.ErrorResponse;
import com.example.springproject.studentmanagement.exceptions.StudentNotFoundException;
import com.example.springproject.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // get all students
    public List<StudentResponseDTO> getAllStudent(){
        List<Student> students = studentRepository.findAll();

        List<StudentResponseDTO> studentResponseDTOList = students.stream()
                .map(StudentMapper::studentEntityToResponse)
                .toList();
        return studentResponseDTOList;

    }

    // Add student
    public StudentResponseDTO addStudent(StudentRequestDTO studentRequestDTO){

        Student student = StudentMapper.requestDTOToEntity(studentRequestDTO);
        Student savedStudent = studentRepository.save(student);
        return StudentMapper.studentEntityToResponse(savedStudent);
    }


    // Update student
    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO updatedStudent){
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new StudentNotFoundException("Student of ID : " + id + " doesn't exist : ")
        );

        student.setStudentName(updatedStudent.getStudentName());
        student.setEmail(updatedStudent.getEmail());
        student.setCourse(updatedStudent.getCourse());
        student.setPassword(updatedStudent.getPassword());

        Student savedStudent = studentRepository.save(student);
        return StudentMapper.studentEntityToResponse(savedStudent);

    }

    //get by ID
    public StudentResponseDTO getStudentById(Long id){

        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student not found of Id: " + id));
        System.out.println("Student found : "+ student);
        return StudentMapper.studentEntityToResponse(student);


    }

    // delete student
    public void deleteStudents(Long id){
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new StudentNotFoundException("Student not found with ID : "+id)
        );
        studentRepository.deleteById(id);
    }


}
