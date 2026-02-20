package com.example.springproject.studentmanagement.controller;

import com.example.springproject.studentmanagement.Entities.Student;
import com.example.springproject.studentmanagement.dto.StudentMapper;
import com.example.springproject.studentmanagement.dto.StudentRequestDTO;
import com.example.springproject.studentmanagement.dto.StudentResponseDTO;
import com.example.springproject.studentmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    //Read all
    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAllStudent(){
        return ResponseEntity.ok(studentService.getAllStudent());
    }

    // Read Student by ID
    @GetMapping("/{studentId}")
    public ResponseEntity<StudentResponseDTO> getStudentDTOById(@PathVariable Long studentId ){

        System.out.println("Calling service");
         return ResponseEntity.ok(studentService.getStudentById(studentId));
    }

    // Create
    @PostMapping
    public ResponseEntity<StudentResponseDTO> addStudents(@RequestBody StudentRequestDTO studentRequestDTO){
        return ResponseEntity.ok(studentService.addStudent(studentRequestDTO));
    }

    // Update Student
    @PutMapping("/{studentId}")
    public ResponseEntity<StudentResponseDTO> updateStudent(@PathVariable Long studentId, @RequestBody StudentRequestDTO studentRequestDTO) {
        return ResponseEntity.ok(studentService.updateStudent(studentId, studentRequestDTO));
    }

    // Delete Student
    @DeleteMapping("/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long studentId){
         studentService.deleteStudents(studentId);
         return ResponseEntity.ok("Student Deleted");
    }
}