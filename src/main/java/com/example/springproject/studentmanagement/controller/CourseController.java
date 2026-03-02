package com.example.springproject.studentmanagement.controller;

import com.example.springproject.studentmanagement.Entities.Course;
import com.example.springproject.studentmanagement.dto.CourseRequestDTO;
import com.example.springproject.studentmanagement.dto.CourseResponseDTO;
import com.example.springproject.studentmanagement.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseResponseDTO> getCourseById(@PathVariable Long courseId){
        return ResponseEntity.ok(courseService.getCourseById(courseId));
    }

    @PostMapping
    public ResponseEntity<CourseResponseDTO> createCourse(@Valid @RequestBody CourseRequestDTO courseRequestDTO){
        return ResponseEntity.ok(courseService.createCourse(courseRequestDTO));
    }

    @GetMapping
    public ResponseEntity<List<CourseResponseDTO>> getCourses(){
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long courseId){
        return ResponseEntity.ok(courseService.deleteCourse(courseId));
    }

    @GetMapping("/getCourseByName/{courseName:.+}")
    public ResponseEntity<List<CourseResponseDTO>> getBookByName(@PathVariable String courseName){
        return ResponseEntity.ok(courseService.getCourseByName(courseName));
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<CourseResponseDTO> updateCourse(@PathVariable Long courseId,
                                                          @Valid @RequestBody CourseRequestDTO courseRequestDTO){

        return ResponseEntity.ok(courseService.updateCourse(courseId, courseRequestDTO));
    }
}
