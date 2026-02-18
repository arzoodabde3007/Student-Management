package com.example.springproject.studentmanagement.controller;

import com.example.springproject.studentmanagement.Entities.Student;
import com.example.springproject.studentmanagement.dto.StudentDTO;
import com.example.springproject.studentmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    //Read all
    @GetMapping("/getStudents")
    public List<Student> getAllStudent(){
        return studentService.getAllStudent();
    }

    // Read Student by ID
    @GetMapping("/studentById/")
    public StudentDTO getStudentDTOById(@RequestParam(name = "studentID") Long studentId ){
        Student student = studentService.getStudentById(studentId);

        StudentDTO studentDTO = new StudentDTO();
        return studentDTO.studentMapper(student);
    }

    // Create
    @PostMapping("/addStudents")
    public Student addStudents(@RequestBody Student student){
        return studentService.addStudent(student);
    }

    // Update Student
    @PutMapping("/updateStudent/")
    public Student updateStudent(@RequestParam Long id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    // Delete Student
    @DeleteMapping("/deleteStudent/")
    public String deleteStudent(@RequestParam Long id){
         studentService.deleteStudents(id);
         return "student deleted";
    }
}
