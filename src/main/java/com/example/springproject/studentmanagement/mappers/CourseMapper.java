package com.example.springproject.studentmanagement.mappers;

import com.example.springproject.studentmanagement.Entities.Course;
import com.example.springproject.studentmanagement.Entities.Department;
import com.example.springproject.studentmanagement.dto.CourseRequestDTO;
import com.example.springproject.studentmanagement.dto.CourseResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public Course requestDTOToEntity(CourseRequestDTO courseRequestDTO , Department department){
        Course course = new Course();
        course.setCourseName(courseRequestDTO.getCourseName());
        course.setCourseHOD(courseRequestDTO.getCourseHOD());
        course.setDepartment(department);
        return course;
    }

    public CourseResponseDTO entityToResponseDTO(Course course){
        CourseResponseDTO courseResponseDTO = new CourseResponseDTO();

        courseResponseDTO.setCourseName(course.getCourseName());
        courseResponseDTO.setCourseHOD(course.getCourseHOD());
        if(course.getDepartment() != null){
            courseResponseDTO.setDepartmentName(course.getDepartment().getDepartmentName());
        }
        return courseResponseDTO;
    }
}
