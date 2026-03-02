package com.example.springproject.studentmanagement.dto;

import com.example.springproject.studentmanagement.Entities.Course;
import com.example.springproject.studentmanagement.Entities.Department;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public Course requestDTOToEntity(CourseRequestDTO courseRequestDTO , Department department){
        Course course = new Course();
        course.setCourseName(courseRequestDTO.getCourseName());
        course.setCourseHOD(courseRequestDTO.getCourseHOD());

        return course;
    }

    public CourseResponseDTO entityToResponseDTO(Course course){
        CourseResponseDTO courseResponseDTO = new CourseResponseDTO();

        courseResponseDTO.setCourseName(course.getCourseName());
        courseResponseDTO.setCourseHOD(course.getCourseHOD());
        courseResponseDTO.setDepartmentName(course.getDepartment().getDepartmentName());
        return courseResponseDTO;
    }
}
