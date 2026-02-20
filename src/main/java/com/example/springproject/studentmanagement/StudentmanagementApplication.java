package com.example.springproject.studentmanagement;

//import com.example.springproject.studentmanagement.Entities.Course;
import com.example.springproject.studentmanagement.Entities.Student;
import com.example.springproject.studentmanagement.dto.StudentResponseDTO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class StudentmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentmanagementApplication.class, args);
//
//        Student student = new Student();
//        student.setStudentName("Bharti");
//        student.setEmail("bhartibaklol@gmail.com");
//
//
//
//        Course course = new Course();
//        course.setCourseHOD("Deepesh");
//        course.setCourseName("xyz");
//
//        student.setCourse(course);
//        course.setStudents(List.of(student));
    }

}
