package com.example.springproject.studentmanagement.service;

import com.example.springproject.studentmanagement.Entities.Course;
import com.example.springproject.studentmanagement.Entities.Department;
import com.example.springproject.studentmanagement.repository.CourseRepository;
import com.example.springproject.studentmanagement.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private CourseRepository courseRepository;
    private DepartmentRepository departmentRepository;

    public CourseService(CourseRepository courseRepository, DepartmentRepository departmentRepository){
        this.courseRepository = courseRepository;
        this.departmentRepository = departmentRepository;
    }

    public Course createCourse(Course course, Long departmentId){
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department not found"));

        course.setDepartment(department);
        return courseRepository.save(course);
    }

    public List<Course> getAllCourses(){
        return courseRepository.findAll();
    }

    public Course getCourseById(Long courseId){
        return courseRepository.findById(courseId)
                .orElseThrow(
                        () -> new RuntimeException("Course Not Found")
                );
    }

    public String deleteCourse(Long courseId){
        courseRepository.deleteById(courseId);
        return "Course removed from database";
    }
}
