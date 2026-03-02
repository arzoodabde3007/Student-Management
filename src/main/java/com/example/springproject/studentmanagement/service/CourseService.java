package com.example.springproject.studentmanagement.service;

import com.example.springproject.studentmanagement.Entities.Course;
import com.example.springproject.studentmanagement.Entities.Department;
import com.example.springproject.studentmanagement.dto.CourseMapper;
import com.example.springproject.studentmanagement.dto.CourseRequestDTO;
import com.example.springproject.studentmanagement.dto.CourseResponseDTO;
import com.example.springproject.studentmanagement.dto.DepartmentMapper;
import com.example.springproject.studentmanagement.repository.CourseRepository;
import com.example.springproject.studentmanagement.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, DepartmentRepository departmentRepository, CourseMapper courseMapper){
        this.courseRepository = courseRepository;
        this.departmentRepository = departmentRepository;
        this.courseMapper = courseMapper;
    }

    // Add course
    public CourseResponseDTO createCourse(CourseRequestDTO courseRequestDTO){

        Department department = departmentRepository.findById(courseRequestDTO.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        Course course =  courseMapper.requestDTOToEntity(courseRequestDTO, department);
        return courseMapper.entityToResponseDTO(courseRepository.save(course));
    }

    public List<CourseResponseDTO> getAllCourses(){
        List<Course> courses =  courseRepository.findAll();

        List<CourseResponseDTO> courseResponseDTOS = courses.stream()
                .map(courseMapper::entityToResponseDTO)
                .toList();

        return courseResponseDTOS;
    }

    // Get course by Id
    public CourseResponseDTO getCourseById(Long courseId){
        Course course =  courseRepository.findById(courseId)
                .orElseThrow(
                        () -> new RuntimeException("Course Not Found")
                );

        return courseMapper.entityToResponseDTO(course);
    }

    // Delete course By id
    public String deleteCourse(Long courseId){
        courseRepository.deleteById(courseId);
        return "Course removed from database";
    }

    // Get course by name
    public List<CourseResponseDTO> getCourseByName(String courseName){
        List<Course> courses = courseRepository.findByCourseName(courseName);

        List<CourseResponseDTO> courseResponseDTOS = courses.stream()
                .map(courseMapper::entityToResponseDTO)
                .toList();

        return courseResponseDTOS;
    }

    // Update course
    public CourseResponseDTO updateCourse(Long courseId, CourseRequestDTO courseRequestDTO){
        Course course = courseRepository.findById(courseId).orElseThrow(
                () -> new RuntimeException("Course not found")
        );

        Department department = departmentRepository.findById(courseRequestDTO.getDepartmentId()).orElseThrow(
                () -> new RuntimeException("Department not fount")
        );

        course.setCourseName(courseRequestDTO.getCourseName());
        course.setCourseHOD(courseRequestDTO.getCourseHOD());
        course.setDepartment(department);

        Course savedCourse  = courseRepository.save(course);
        return courseMapper.entityToResponseDTO(savedCourse);


    }
}
