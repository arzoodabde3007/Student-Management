package com.example.springproject.studentmanagement.service;

import com.example.springproject.studentmanagement.Entities.Student;
import com.example.springproject.studentmanagement.dto.StudentResponseDTO;
import com.example.springproject.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // get all students
    public List<StudentResponseDTO> getAllStudent(){
        List<Student> students = studentRepository.findAll();

        List<StudentResponseDTO> studentResponseDTOList = students.stream()
                .map(student -> new StudentResponseDTO().studentMapper(student))
                .toList();
        return studentResponseDTOList;

    }

    // Add student
    public Student addStudent(Student student){
        return studentRepository.save(student);
    }


    // Update student
    public Student updateStudent(Long id, Student updatedStudent){
        Student student = studentRepository.findById(id).get();
        student.setStudentName(updatedStudent.getStudentName());
        student.setEmail(updatedStudent.getEmail());
        student.setCourse(updatedStudent.getCourse());
        return studentRepository.save(student);
    }

    //get by ID
    public Student getStudentById(Long id){
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    // delete student
    public void deleteStudents(Long id){
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Student not found")
        );
        studentRepository.deleteById(id);
    }


}
