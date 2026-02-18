package com.example.springproject.studentmanagement.service;

import com.example.springproject.studentmanagement.Entities.Student;
import com.example.springproject.studentmanagement.dto.StudentDTO;
import com.example.springproject.studentmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    // get all students
    public List<Student> getAllStudent(){
        return studentRepository.findAll();
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
        Student student = studentRepository.findById(id).get();
        studentRepository.deleteById(id);
    }


}
