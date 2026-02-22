package com.example.springproject.studentmanagement.controller;

import com.example.springproject.studentmanagement.Entities.Course;
import com.example.springproject.studentmanagement.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long courseId){
        return ResponseEntity.ok(courseService.getCourseById(courseId));
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course course,
                                               @RequestParam Long departmentId){
        return ResponseEntity.ok(courseService.createCourse(course, departmentId));
    }

    @GetMapping
    public ResponseEntity<List<Course>> getCourses(){
        return ResponseEntity.ok(courseService.getAllCourses());
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long courseId){
        return ResponseEntity.ok(courseService.deleteCourse(courseId));
    }
}
