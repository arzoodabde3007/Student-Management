//package com.example.springproject.studentmanagement.Entities;
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.List;
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class Course {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer courseId;
//    private String courseName;
//    private String courseHOD;
//
//    @OneToMany(mappedBy = "course" , cascade = CascadeType.ALL)
//    private List<Student> students;
//}
