package com.example.springproject.studentmanagement.controller;

import com.example.springproject.studentmanagement.Entities.Student;
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
    @GetMapping("/getStudents")
    public ResponseEntity<List<StudentResponseDTO>> getAllStudent(){
        return ResponseEntity.ok(studentService.getAllStudent());
    }

    // Read Student by ID
    @GetMapping("/studentById/")
    public ResponseEntity<StudentResponseDTO> getStudentDTOById(@RequestParam(name = "studentID") Long studentId ){

        Student student = studentService.getStudentById(studentId);
        StudentResponseDTO studentDTO = new StudentResponseDTO();
         return ResponseEntity.ok(studentDTO.studentMapper(student));
    }

    // Create
    @PostMapping("/addStudents")
    public ResponseEntity<StudentResponseDTO> addStudents(@RequestBody Student student){
        student.setStudentId(0);
         Student insertedStudent = studentService.addStudent(student);
        StudentResponseDTO studentDTO = new StudentResponseDTO();
         return ResponseEntity.ok(studentDTO.studentMapper(insertedStudent));
    }

    // Update Student
    @PutMapping("/updateStudent/")
    public ResponseEntity<Student> updateStudent(@RequestParam Long id, @RequestBody Student student) {
        return ResponseEntity.ok(studentService.updateStudent(id, student));
    }

    // Delete Student
    @DeleteMapping("/deleteStudent/")
    public ResponseEntity<String> deleteStudent(@RequestParam Long studentId){
         studentService.deleteStudents(studentId);
         return ResponseEntity.ok("Student Deleted");
    }
}